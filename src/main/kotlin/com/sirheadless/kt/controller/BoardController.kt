package com.sirheadless.kt.controller

import com.sirheadless.kt.*
import com.sirheadless.kt.Messages.*
import com.sirheadless.kt.game.Game
import com.sirheadless.kt.game.GamesManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.*
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.annotation.*
import org.springframework.stereotype.*

import java.security.Principal
import java.util.logging.Logger

/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 2:57 PM
 * Test
 */

@Controller
open class BoardController
@Autowired
constructor(private val messagingTemplate: SimpMessageSendingOperations) {

    val logger = Logger.getLogger(BoardController::class.java.toString())

    @Autowired
    lateinit var board: Board

    @SubscribeMapping("/connect")
    @SendToUser("/topic/loadGame")
    fun join(principal: Principal) : LoadGameMessage {
        var game: Game? = GamesManager.findActiveGameOfUser(principal.name)
        game?.let{
            return  LoadGameMessage(true,it.getBoardWithString(), it.getSymbolForPlayer(principal.name), it.getOpponentForPlayer(principal.name), it.turn.symbol)        }
        return LoadGameMessage(false)
    }


    @MessageMapping("/setField")
    fun setField(principal: Principal, fieldMessage: FieldMessage) {
        var game : Game? = GamesManager.findGameOfUser(principal.name)
        if (game == null) {
            messagingTemplate.convertAndSendToUser(principal.name, "/topic/error", "Your game was could not be found!")
            return
        }
        if(game.setField(fieldMessage)) {
            val fieldMessage= FieldMessage(fieldMessage.field, fieldMessage.player)
            game?.let{ sendMessageToPlayersInGame(fieldMessage, "/topic/setField", it)}
        } else {
            messagingTemplate.convertAndSendToUser(principal.name, "/topic/error", ErrorMessage("It is not your turn my little fellow!"))
            return
        }

        var gameStatus = game.getGameStatus()
        if (gameStatus.isOver()) {
            val winnerMessage = GameOverMessage(gameStatus)
            game?.let{sendMessageToPlayersInGame(winnerMessage, "/topic/gameOver", it)}
        }

    }

    @MessageMapping("/newGame")
    fun newGame(principal: Principal) {
        var game: Game? = GamesManager.findGameOfUser(principal.name)
        game?.let{
            GamesManager.resetGame(it)
            sendMessageToPlayersInGame(NewGameMessage(PlayerType.PLAYERX.symbol, it.playerO), NewGameMessage(PlayerType.PLAYERO.symbol, it.playerX),"/topic/newGame", it)
        } ?: run {
            messagingTemplate.convertAndSendToUser(principal.name, "/topic/error", ErrorMessage("You have no game right now. Check for new opponent"))
        }
    }

    @MessageMapping("/findOpponent")
    fun findOpponent(principal: Principal) {
        logger.info("Looking for opponent for ${principal.name}")
        var game: Game? = GamesManager.addUserToGame(principal.name)
        game?.let { sendMessageToPlayersInGame(NewGameMessage(PlayerType.PLAYERX.symbol, it.playerO), NewGameMessage(PlayerType.PLAYERO.symbol, it.playerX), "/topic/newGame", it) }
    }

	@MessageMapping("/loadGame")
	fun loadGame(principal: Principal) {
		var game: Game? = GamesManager.findGameOfUser(principal.name)
        game?.let{
		    var loadGameMessage = LoadGameMessage(true, it.getBoardWithString(), it.getSymbolForPlayer(principal.name), it.getOpponentForPlayer(principal
                    .name), it.turn.symbol)
            sendMessageToPlayersInGame(loadGameMessage, "/topic/loadGame", it)
        }
	}

    private fun sendMessageToPlayersInGame(message : Any, destination: String, game: Game) {
            messagingTemplate.convertAndSendToUser(game!!.playerX, destination, message)
            messagingTemplate.convertAndSendToUser(game!!.playerO, destination, message)
    }

    private fun sendMessageToPlayersInGame(messagePlayerX: Any, messagePlayerY: Any, destination : String , game: Game) {
            messagingTemplate.convertAndSendToUser(game!!.playerX, destination, messagePlayerX)
            messagingTemplate.convertAndSendToUser(game!!.playerO, destination, messagePlayerY)
    }

}