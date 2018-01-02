package com.sirheadless.kt

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*
import java.util.logging.Logger

/**
 * Created by
 * User: creuter
 * Date: 12/28/2017
 * Time: 11:54 AM
 * Test
 */

@Configuration
@EnableWebSocketMessageBroker
open class XWebSocketConfigOLD : AbstractWebSocketMessageBrokerConfigurer() {
	val logger = Logger.getLogger(XWebSocketConfigOLD::class.java.toString())



	override fun registerStompEndpoints(registry: StompEndpointRegistry?) {
		logger.info("Connected to StompEndpoint")
		registry!!.addEndpoint("/gs-guide-websocket").withSockJS()
	}

	override fun configureMessageBroker(registry: MessageBrokerRegistry?) {
		logger.info("Message info configured")
		registry!!.enableSimpleBroker("/toClient")
		registry.setApplicationDestinationPrefixes("/toServer")
	}

}