package com.sirheadless.kt

import org.springframework.messaging.handler.annotation.*
import org.springframework.stereotype.Controller

/**
 * Created by
 * User: creuter
 * Date: 12/28/2017
 * Time: 11:51 AM
 * Test
 */
@Controller
open class GreetingController{


	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	fun greeting(message: HelloMessage) : Greeting {
		Thread.sleep(1000)
		return Greeting("Hello, ${message.name} !")
	}
}