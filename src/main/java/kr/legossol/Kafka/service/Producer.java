package kr.legossol.Kafka.service;

import java.util.Properties;
import java.util.stream.IntStream;
import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.ProducerBatch;
import org.apache.kafka.clients.producer.internals.RecordAccumulator;
import org.apache.kafka.clients.producer.internals.StickyPartitionCache;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.record.MemoryRecordsBuilder;
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

//    @Getter
//    @ToString
//    public static class detailMessage{}


    public void sendMessage(Message message){

        this.kafkaTemplate.send(TOPIC,message);
        ListenableFuture<SendResult<String,Message>> future =
                kafkaTemplate.send(TOPIC,"1",message);


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
    /**파티션 지정 프로듀싱 방법
     * 테스트를와 학습을 위해 props 선언 - 다양한 서비스 있을 경우 각각의 커스터마이징 용의
     * ack 1 def아님 def 는 -1
     * */
//    public void sendWithKey(){
//        long start = System.currentTimeMillis();
//        String firstKey = "1";
//        String secondKey = "2";
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");
//        props.put("acks", "1");
//
//        try(org.apache.kafka.clients.producer.Producer<String, Message> producer = new KafkaProducer<>(props)){
//
//            IntStream.rangeClosed(1, 10).forEach(i->{
//                if(i%2==1) {
//                    producer.send(new ProducerRecord<String, Message>(TOPIC, firstKey ,i+" - Apache Kafka is a distributed streaming platform-sendWithKey() oddKey "+firstKey));
//                }else {
//                    producer.send(new ProducerRecord<String, Message>(TOPIC, secondKey ,i+" - Apache Kafka is a distributed streaming platform-sendWithKey() evenKey "+secondKey));
//                }
//            });
//
//
//        }catch (Exception e) {}
//        long end = System.currentTimeMillis();
//        log.info("sendWithKey() - during time : "+ (end-start));
//    }



//    public void stickyProduce(Message message){
//        String batchPart = "2";
//        MemoryRecordsBuilder memoryRecordsBuilder = new MemoryRecordsBuilder();
//        ProducerBatch batch = new ProducerBatch(new TopicPartition(TOPIC,1),);
//
//    }
}
