package kr.legossol.Kafka.service;

import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class Producer {
    private static final String TOPIC = "chat";
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Getter
//    @ToString
//    public static class detailMessage{}


    public void sendMessage(Message message){

        ListenableFuture<SendResult<String,Message>> future =
                kafkaTemplate.send(TOPIC,message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("MESSAGE SENDING FAIL DUE TO = {}",ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                log.info("SUCCEED TO SEND MESSAGE=["+result.getProducerRecord().value().toString()+
                        "] WITH OFFSET = "+result.getRecordMetadata().offset()+"]");
            }

        });
    }
}
