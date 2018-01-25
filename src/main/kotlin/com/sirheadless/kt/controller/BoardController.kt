package com.sirheadless.kt.controller

import com.sirheadless.kt.Board
import com.sirheadless.kt.Messages.FieldMessage
import com.sirheadless.kt.Player
import com.sirheadless.kt.game.GamesManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.*
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.annotation.SubscribeMapping

import org.springframework.stereotype.Controller
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

    @SubscribeMapping("/join")
    fun join(): FieldMessage {
        logger.info("Somebody joined !!!")
        val returnMessage: FieldMessage = FieldMessage(9, "x")
        return returnMessage
    }


    @MessageMapping("/setField")
    @SendTo("/toClient/setField")
    fun setField(headerAccessor: SimpMessageHeaderAccessor, fieldMessage: FieldMessage): FieldMessage {
        board.setField(fieldMessage.field.toInt(), Player.PLAYERX)
        val returnMessage: FieldMessage = FieldMessage(fieldMessage.field, "x")
        logger.info(returnMessage.toString())
        return returnMessage
    }

    @MessageMapping("/newGame")
    @SendTo("/toClient/newGame")
    fun newGame(test: String): String {
        logger.info("New game")
        return ""
    }


}