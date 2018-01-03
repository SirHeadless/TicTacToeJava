package com.sirheadless.kt

import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.context.ApplicationListener
import java.util.logging.Logger


/**
 * Created by
 * User: creuter
 * Date: 1/3/2018
 * Time: 4:11 PM
 * Test
 */
class StompConnectEvent : ApplicationListener<SessionConnectEvent> {

	private val logger = Logger.getLogger(StompConnectEvent::class.java.toString())

	override fun onApplicationEvent(event: SessionConnectEvent) {
		val sha = StompHeaderAccessor.wrap(event.message)

		val company = sha.getNativeHeader("company")[0]
		logger.info("Connect event [sessionId: " + sha.sessionId + "; company: " + company + " ]")
	}
}