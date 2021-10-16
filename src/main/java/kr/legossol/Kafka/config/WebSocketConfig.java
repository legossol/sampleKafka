package kr.legossol.Kafka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //프론트 app.js에서 받을 url
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistryregistry){
        stompEndpointRegistryregistry.addEndpoint("/legossol-chat").setAllowedOrigins("*").withSockJS();
    }

    //프론트에서 소켓 클라이언트 연결할때 사용
    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry){
        messageBrokerRegistry.setApplicationDestinationPrefixes("/kafka");
        messageBrokerRegistry.enableSimpleBroker("/topic/");
    }
}
