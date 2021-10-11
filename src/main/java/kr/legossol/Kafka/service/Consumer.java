package kr.legossol.Kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class Consumer {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "chat",groupId = "chat_group")
    public void consume(String message) throws IOException{

      log.info("#### -> Consumed message -> {}",message);
      template.convertAndSend("/topic/group", message);
    }
}
