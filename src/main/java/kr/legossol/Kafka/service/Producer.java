package kr.legossol.Kafka.service;

import java.util.Properties;
import java.util.stream.IntStream;
import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
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
    private final String relKey = "1";
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(Message message){
        log.info(String.format("Produce message : %s",message));

        this.kafkaTemplate.send(TOPIC,message);
        ListenableFuture<SendResult<String,Message>> future =
                kafkaTemplate.send(TOPIC, kafkaTemplate.getTransactionIdPrefix(),message);

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
//    public void partitionKeySend(Message message){
//        try(true){
//            IntStream.rangeClosed(1,10).forEach(i->{
//                if(i%2 ==1){
//                    kafkaTemplate.send(new ProducerRecord<String,Message>(TOPIC,relKey,i+"-send With Key"+relKey));
//                }
//            });
//        }
//    }
}
