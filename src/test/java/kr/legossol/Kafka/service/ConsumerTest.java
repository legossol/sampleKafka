package kr.legossol.Kafka.service;

import kr.legossol.Kafka.messageDto.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


class ConsumerTest {

    SimpMessagingTemplate template;

    @Test
    void consume() throws IOException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "chat_group");
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "latest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.springframework.kafka.support.serializer.JsonDeserializer");

        KafkaConsumer<String, Message> consumer = new KafkaConsumer<String, Message>(props);
        consumer.subscribe(Arrays.asList("chat"));
        try {
            while (true) {
                ConsumerRecords<String, Message> records = consumer.poll(500);
                for (ConsumerRecord<String, Message> record : records) {
                    System.out.printf("Topic: %s, Partition: %s, Offset: %d, Key: %s, Value: %n\n",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }

    @Test
    public void testConsume(Message message){
        template.convertAndSend("/topic/group",message);
    }
}