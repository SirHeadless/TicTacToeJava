package com.sirheadless.kt.controller

import com.sirheadless.kt.Messages.NewGameMessage
import com.sirheadless.kt.StompConnectEvent
import com.sirheadless.kt.game.Game
import com.sirheadless.kt.game.GamesManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.socket.messaging.SessionConnectEvent
import java.util.logging.Logger

@Component
open class TestController : ApplicationListener<SessionConnectEvent> {
    override fun onApplicationEvent(event: SessionConnectEvent?) {
        println("Hello")
        messagingTemplate.convertAndSendToUser("u1", "/toClient/newGame", NewGameMessage("x"))
        messagingTemplate.convertAndSendToUser("u2" ,"/toClient/newGame", NewGameMessage("y"))

    }

    @Autowired
    constructor( messagingTemplate: SimpMessageSendingOperations){
        this.messagingTemplate = messagingTemplate
    }
//

    lateinit var messagingTemplate: SimpMessageSendingOperations

    private val logger = Logger.getLogger(TestController::class.java.toString())


    fun sendPlayersGameStarts(game: Game) {
        Thread.sleep(2000)
        messagingTemplate.convertAndSendToUser(game.playerX, "/toClient/newGame", NewGameMessage("x"))
        messagingTemplate.convertAndSendToUser(game.playerY, "/toClient/newGame", NewGameMessage("y"))

    }

}