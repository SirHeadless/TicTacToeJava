package com.sirheadless.kt.Listeners

import org.springframework.context.ApplicationListener
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.messaging.simp.SimpMessageSendingOperations



/**
 * Created by
 * User: creuter
 * Date: 1/29/2018
 * Time: 8:44 AM
 * Test
 */

//class WebSocketConnectHandler : ApplicationListener<SessionConnectEvent> {
//
//	var messagingTemplate:SimpMessageSendingOperations
//
//
//	constructor(messagingTemplate :SimpMessageSendingOperations) {
//		this.messagingTemplate = messagingTemplate;
//	}
//
//	override fun onApplicationEvent(event: SessionConnectEvent) {
//		event.user?.let {
//			messagingTemplate.convertAndSendToUser(it.name, "/topic/test", "This is just a test")
//			messagingTemplate.convertAndSendToUser("u1", "/topic/test", "This is just a test")
//		}
//	}
//
//
//}