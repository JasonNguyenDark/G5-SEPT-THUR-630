package com.example.backend.stuff;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//The class is annotated with @Configuration to indicate that it is a Spring configuration class.
@Configuration
//@EnableWebSocketMessageBroker enables WebSocket message handling, backed by a message broker.
// It also designates the /app prefix for messages that are bound for methods annotated with @MessageMapping.
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    // the configureMessageBroker() method implements the default method in WebSocketMessageBrokerConfigurer.
    // to configure the message broker.
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //It starts by calling enableSimpleBroker() to enable a simple memory-based
        // message broker to carry the greeting messages back to the client on
        // destinations prefixed with /topic.

        config.enableSimpleBroker("/topic");
        // This prefix (topic) will be used to define all the message mappings.
        // For example, /app/hello is the endpoint
        // that the GreetingController.greeting() method is mapped to handle.

        config.setApplicationDestinationPrefixes("/app");
    }
    @Override
    // The registerStompEndpoints() method registers the the path (/gs-guide-websocket) endpoint,
    // enabling SockJS fallback options so that alternate transports can be used if WebSocket is not available.

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // The SockJS client will attempt to connect to the path (/gs-guide-websocket)
        // and use the best available transport (websocket, xhr-streaming, xhr-polling, and so on).
        registry.addEndpoint("/chat").withSockJS();
    }

}