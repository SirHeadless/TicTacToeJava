package com.sirheadless.kt

import com.google.gson.Gson
import org.springframework.stereotype.Component
import org.springframework.web.socket.*
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.IOException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.logging.Logger


/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 8:35 PM
 * Test
 */

@Component
class SocketHandler : TextWebSocketHandler() {
	internal var sessions: MutableList<WebSocketSession>  = CopyOnWriteArrayList()
	val logger = Logger.getLogger(SocketHandler::class.java.toString())


	@Throws(InterruptedException::class, IOException::class)
	override fun handleTextMessage(session: WebSocketSession?, message: TextMessage?) {
		for(webSocketSession in sessions) {
			logger.info("MESSAGE ${message?.payload.toString()}")
			val value = Gson().fromJson(message!!.payload, Map::class.java)
			webSocketSession.sendMessage(TextMessage("Moin ${value["name"]}  !"))
		}

	}

	@Throws(Exception::class)
	override fun afterConnectionEstablished(session: WebSocketSession?) {
		//the messages will be broadcasted to all users.
		if (session != null) {
			sessions.add(session)
		}
	}

}