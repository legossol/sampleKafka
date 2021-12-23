package kr.legossol.Kafka.service;

import java.util.Properties;
import java.util.stream.IntStream;
import kr.legossol.Kafka.messageDto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.internals.ProducerBatch;
import org.apache.kafka.clients.producer.internals.RecordAccumulator;
import org.apache.kafka.clients.producer.internals.StickyPartitionCache;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.record.MemoryRecordsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {
    private static final String TOPIC = "chat";
    private final String relKey = "1";
    private final KafkaTemplate<String, Message> kafkaTemplate;


//    @Getter
//    @ToString
//    public static class detailMessage{}


    public void sendMessage(Message message){

        this.kafkaTemplate.send(TOPIC,message);
        ListenableFuture<SendResult<String,Message>> future =
                kafkaTemplate.send(TOPIC,message);


        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("MESSAGE SENDING FAIL DUE TO = {}",ex.getMessage());
//                log.info("Message Partition ={}", ex.);
            }

            @Override
            public void onSuccess(SendResult<String, Message> result) {
                log.info("SUCCEED TO SEND MESSAGE=["+result.getProducerRecord().value().toString()+
                        "] WITH OFFSET = "+result.getRecordMetadata().offset()+"] PARTITION = [" +
                    result.getRecordMetadata().partition()+ "] WITH HASOFFSER =["+
                    result.getRecordMetadata().hasOffset() + " ]");
            }
        });
    }

//    public static void main(String[] args) throws InterruptedException {
//        Message message = new Message();
//        message.setAuthor("테스트 진행자 ");
//        message.setContent("두루두루");
//        Properties props = new Properties();
//        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
//        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//            "org.apache.kafka.common.serialization.StringSerializer");
//        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//            "org.springframework.kafka.support.serializer.JsonSerializer");
//        props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,
//            "kr.legossol.Kafka.service.ForAssignProduceCustomPartition");
//        //멱등성 보장을 위한 설정 아래
////        props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
////        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
////        props.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
////        props.setProperty(ProducerConfig.RETRIES_CONFIG, "5");
////        props.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "proto-transaction-01");
//
//        KafkaTemplate<String, Message> kafkaTemplate = new KafkaTemplate<>(props);
////        KafkaProducer<String, Message> producer = new KafkaProducer<String, Message>(props);
////        producer.initTransactions();//프로듀서 트랜잭션 초기화
////        producer.beginTransaction();//트랙잭션 시작
//
//        Producer producer = new Producer(kafkaTemplate);
//        for(int i = 0; i < 10; i++){
//            producer.sendMessage(message);
//            Thread.sleep(4000);
//        }
//
//
//    }
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
