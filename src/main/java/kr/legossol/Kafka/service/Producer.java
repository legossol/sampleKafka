package kr.legossol.Kafka.service;

import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {
    private static final String TOPIC = "chat";
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(Message message){
        log.info(String.format("Produce message : %s",message));

        this.kafkaTemplate.send(TOPIC,message);
    }
}
