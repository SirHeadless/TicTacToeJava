package com.sirheadless.kt

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.*
import java.util.logging.Logger
import kotlin.math.log

/**
 * Created by
 * User: creuter
 * Date: 12/30/2017
 * Time: 8:19 AM
 * Test
 */

@Configuration
@EnableWebSocket
open class WebSocketConfig : WebSocketConfigurer {
	val logger = Logger.getLogger(WebSocketConfig::class.java.toString())


	override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry?) {
		logger.info("connected to second handler")
		registry!!.addHandler(SocketHandler(), "/name")
	}

}