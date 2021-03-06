package kr.legossol.Kafka.service;

import java.util.Properties;
import kr.legossol.Kafka.messageDto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.UniformStickyPartitioner;
import org.apache.kafka.clients.producer.internals.DefaultPartitioner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AssignPartitionProducer {

  public void partitionSend(Message message) {
    Properties props = new Properties();
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
    props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringSerializer");
    props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        "org.springframework.kafka.support.serializer.JsonSerializer");
//    props.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,
//        DefaultPartitioner.class.getName());
    //멱등성 보장을 위한 설정 아래
//    props.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
//    props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
//    props.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
//    props.setProperty(ProducerConfig.RETRIES_CONFIG, "5");
    props.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,"300");
    props.setProperty(ProducerConfig.LINGER_MS_CONFIG,"5000000");
    props.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "proto-transaction-01");

    KafkaProducer<String, Message> producer = new KafkaProducer<String, Message>(props);
    producer.initTransactions();//프로듀서 트랜잭션 초기화
    producer.beginTransaction();//트랙잭션 시작
    try {
      //producerRecord를 통해 인자로 받을 수 있는 요소 (topic, partition, timestamp, key, value, headers)
      ProducerRecord<String, Message> record = new ProducerRecord<String, Message>(
          "chat",
//          1,
          "hi",
          message);

      producer.send(record, new Callback() {
        @Override
        public void onCompletion(RecordMetadata metadata, Exception exception) {
          if (exception != null) {
            exception.printStackTrace();
          }
          log.error("SUCCEED TO PRODUCE = [" + metadata.toString() + metadata.topic() +
              "PARTITION ==" + metadata.partition() +
              "Topic==["+metadata.topic()+
              "] WITH OFFSET = " + metadata.offset() + "]");
        }
      });
    } catch (Exception e) {
      producer.abortTransaction();//트랜잭션 중단
      e.printStackTrace();
    } finally {
      producer.commitTransaction();//트랜잭션 커밋
      producer.close();
    }
  }
}
