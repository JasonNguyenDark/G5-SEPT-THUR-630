package com.example.backend.stuff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.HtmlUtils;

// API source: https://spring.io/guides/gs/messaging-stomp-websocket/

//In Spring’s approach to working with STOMP messaging,
// STOMP messages can be routed to @Controller classes.
@Controller
public class GreetingController {

    // The @MessageMapping annotation ensures that, if a message is sent to the
    // /hello destination, the greeting() method is called.
    @MessageMapping("/hello")

    // The greeting() method creates a Greeting object and returns it.
    // The return value is broadcast to all subscribers of /topic/greetings, as specified in the
    // @SendTo annotation.
    @SendTo("/topic/greetings")

    public Greeting greeting(HelloMessage message) throws Exception {
        // Can do Thread.sleep(1000) to simulate asynchronous messaging.
        Greeting greeting = new Greeting();
        greeting.setContent("Hello" + HtmlUtils.htmlEscape(message.getName()) + "!");
        return greeting;
    }

}
