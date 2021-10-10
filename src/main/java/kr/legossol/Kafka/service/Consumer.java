package kr.legossol.Kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class Consumer {
    @KafkaListener(topics = "chat_topic",groupId = "chat_group")
    public void consume(String message) throws IOException{

      log.info("#### -> Consumed message -> {}",message);
    }
}
