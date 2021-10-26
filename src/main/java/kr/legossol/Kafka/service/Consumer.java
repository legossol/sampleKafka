package kr.legossol.Kafka.service;

import kr.legossol.Kafka.ListenerTopic;
import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class Consumer {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "chat",groupId = "chat_group",
        topicPartitions ={@TopicPartition(topic = "chat", partitions = "0")})

    public void consume(Message message) throws ExecutionException, InterruptedException {

        log.info("#### -> FIRST CONSUMER SEND MESSAGE -> {}",message);

        ListenerTopic listenerTopics = new ListenerTopic();
        listenerTopics.showTopicInfo();

        template.convertAndSend("/topic/group",message);
    }
}
