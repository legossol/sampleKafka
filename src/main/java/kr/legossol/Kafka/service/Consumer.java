package kr.legossol.Kafka.service;

import kr.legossol.Kafka.ListenerTopic;
import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerGroupMetadata;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class Consumer {
    @Autowired
    SimpMessagingTemplate template;
    int consumeCnt =0;
    @KafkaListener(topics = "chat"
        ,id = "chat_id"
//        ,groupId = "chat_group"
//        ,topicPartitions ={@TopicPartition(topic = "chat", partitions = "0")}
    )

    public void consume(Message message, Acknowledgment acknowledgment) throws ExecutionException, InterruptedException {
      ConsumerGroupMetadata groupMetadata = new ConsumerGroupMetadata("ssolGroupId");
      log.error("groupMetadata====={}",groupMetadata);
        log.info("#### -> FIRST CONSUMER SEND MESSAGE -> {}",message);

//        ListenerTopic listenerTopics = new ListenerTopic();
//        listenerTopics.showTopicInfo();
        consumeCnt++;
        template.convertAndSend("/topic/group",message);
        log.info("consumeCnt========={}",consumeCnt);

        acknowledgment.acknowledge();

    }
}
