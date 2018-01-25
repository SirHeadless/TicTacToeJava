package com.sirheadless.kt

import com.sirheadless.kt.Messages.NewGameMessage
import com.sirheadless.kt.game.Game
import com.sirheadless.kt.game.GamesManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.context.ApplicationListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component
import java.util.logging.Logger
import org.springframework.messaging.simp.SimpMessageType
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import java.util.Arrays.asList
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event
import java.util.*


/**
 * Created by
 * User: creuter
 * Date: 1/3/2018
 * Time: 4:11 PM
 * Test
 */
@Component
class StompConnectEvent : ApplicationListener<SessionConnectEvent> {

    private val logger = Logger.getLogger(StompConnectEvent::class.java.toString())

    @Autowired
    lateinit var messagingTemplate: SimpMessageSendingOperations


    override fun onApplicationEvent(event: SessionConnectEvent) {

        var game : Game? = GamesManager.addUserToGame(event.user.name)
        if (game  != null ) {
            sendPlayersGameStarts(game)
            sendMessageToOtherPlayer(event)
        }

    }

    private fun sendPlayersGameStarts(game : Game) {
        Thread.sleep(2000)
        messagingTemplate.convertAndSendToUser(game.playerX, "/toClient/newGame", NewGameMessage("x"))
        messagingTemplate.convertAndSendToUser(game.playerY, "/toClient/newGame", NewGameMessage("y"))

    }

    private fun sendMessageToOtherPlayer(event: SessionConnectEvent) {
        val headers = event.message.headers
        val user = SimpMessageHeaderAccessor.getUser(headers) ?: return
        val id = SimpMessageHeaderAccessor.getSessionId(headers)
//        repository.save(ActiveWebSocketUser(id, user.name, Calendar.getInstance()))
        messagingTemplate.convertAndSend("/toClient/newGame", Arrays.asList(user.name))
    }
}