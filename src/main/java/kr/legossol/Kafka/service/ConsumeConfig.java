package kr.legossol.Kafka.service;

import java.util.HashMap;
import java.util.Map;
import kr.legossol.Kafka.messageDto.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;
@Configuration
public class ConsumeConfig {

  @Bean
  public ConsumerFactory<String, Message> consumerFactory1() {
    JsonDeserializer<Message> deserializer = new JsonDeserializer<>(Message.class);
    deserializer.setRemoveTypeHeaders(false);
//    deserializer.addTrustedPackages("kr.co.parkingcloud.site");
    deserializer.setUseTypeMapperForKey(true);

    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
    props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    props.put(ConsumerConfig.GROUP_ID_CONFIG,"CONSUMER-GROUP-ID-CONFIG-SET");
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "Instants_id-parkUnique");
    // read_committed : transaction 이 committed 된 메시지만 읽겠다는 의미
    props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
    // broker 에서 consumer 를 트래킹 가능하게 해주는 id
    props.put(ConsumerConfig.CLIENT_ID_CONFIG, "PARKINGLOT-CONSUME");

    return new DefaultKafkaConsumerFactory<>(props,
        new StringDeserializer(),
        deserializer);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory1());
    ContainerProperties props = factory.getContainerProperties();
    props.setAckMode(AckMode.MANUAL_IMMEDIATE);
    return factory;
  }
}
