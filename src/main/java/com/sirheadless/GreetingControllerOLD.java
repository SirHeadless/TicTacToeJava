//package com.sirheadless;
//
//import com.sirheadless.kt.Delete.Greeting;
//import com.sirheadless.kt.Delete.HelloMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//
///**
// * Created by
// * User: creuter
// * Date: 12/28/2017
// * Time: 10:45 AM
// * Test
// */
//@Controller
//public class GreetingControllerOLD {
//
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception{
//        Thread.sleep(1000);
//        return  new Greeting("Hello, " + message.getName() + "!");
//    }
//}
