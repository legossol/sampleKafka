package kr.legossol.Kafka.config;


import kr.legossol.Kafka.service.Consumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {
//    @Value("${kafka.consumer.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${kafka.consumer.group-id}")
//    private String groupId;
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(factory.getConsumerFactory());
//        factory.getContainerProperties().setPollTimeout(5000);
//        public Consumer consumer(){
//            return new Consumer();
//        }
//        public ConsumerFactory<String,String> consumerFactory(){
//            return new DefaultKafkaConsumerFactory<>(Kafka)
//        }
//    }
}
