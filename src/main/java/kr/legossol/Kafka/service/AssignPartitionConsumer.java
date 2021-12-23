package kr.legossol.Kafka.service;

import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
public class AssignPartitionConsumer {
//  @KafkaListener(topicPartitions = @TopicPartition(topic = "chat", partitions = "2",
//      partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0")))
//  public void listenAndSend2(@Headers MessageHeaders headers, @Payload Message message) {
//    log.info("Headers : {}", headers.toString());
//    log.info("Consumed Message : {}",F message);
//  }
//
//  @KafkaListener(topicPartitions = @TopicPartition(topic = "chat", partitions = "1",
//      partitionOffsets = @PartitionOffset(partition = "*", initialOffset = "0")))
//  public void listenAndSend3(@Headers MessageHeaders headers, @Payload Message message) {
//    log.info("Headers : {}", headers.toString());
//    log.info("Consumed Message : {}", message);
//  }
//
//  @Bean
//  public PartitionFinder finder(ConsumerFactory<String, String> consumerFactory) {
//    return new PartitionFinder(consumerFactory);
//  }
//
//  public static class PartitionFinder {
//
//    private final ConsumerFactory<String, String> consumerFactory;
//
//    public PartitionFinder(ConsumerFactory<String, String> consumerFactory) {
//      this.consumerFactory = consumerFactory;
//    }
//
//    public String[] partitions(String topic) {
//      try (Consumer<String, String> consumer = consumerFactory.createConsumer()) {
//        return consumer.partitionsFor(topic).stream()
//            .map(pi -> "" + pi.partition())
//            .toArray(String[]::new);
//      }
//    }
//  }
}
