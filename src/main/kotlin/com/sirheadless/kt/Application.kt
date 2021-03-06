package com.sirheadless.kt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.*
import org.springframework.messaging.simp.SimpMessageSendingOperations

/**
 * Created by
 * User: creuter
 * Date: 12/28/2017
 * Time: 12:00 PM
 * Test
 */

@SpringBootApplication
open class Application {


	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(Application::class.java, *args )
		}

//		@Configuration
//		internal open class WebSocketHandlersConfig {
//
//			@Bean
//			open fun webSocketConnectHandler(messagingTemplate: SimpMessageSendingOperations): WebSocketConnectHandler {
//				return WebSocketConnectHandler(messagingTemplate)
//			}
//
//		}

	}




}

