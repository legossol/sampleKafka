package kr.legossol.Kafka;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicAdmin {
//  @Value("${kafka.bootstrap-servers}")
//  private String bootstrapserver;

//  @Bean
//  public KafkaAdmin admin(){
//    Map<String, Object> config = new HashMap<>();
//    config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//    return new KafkaAdmin(config);
//  }
//
//  /**
//   * 2.7버전 부터는 아래와같이 만드는것이 가능
//   * **/
//  @Bean
//  public KafkaAdmin.NewTopics topicsGenerator(){
//    return new NewTopics(
//        TopicBuilder.name("chatting")
//            .partitions(3)
//            .replicas(3)
//            .build(),
//        TopicBuilder.name("project")
//            .partitions(1)
//            .build());
//  }


    /*2.5버전이하에서는 아래와같은 식으로 만들어왔다.*/

//  @Bean
//  public NewTopic chat(){
//    return TopicBuilder.name("chat")
//        .partitions(3)
//        .replicas(3)
//        .compact()
//        .build();
//  }
//  public NewTopic topic2() {
//    return TopicBuilder.name("thing2")
//        .partitions(10)
//        .replicas(3)
//        .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
//        .build();
//  }
//
//  @Bean
//  public NewTopic topic3() {
//    return TopicBuilder.name("thing3")
//        .assignReplicas(0, Arrays.asList(0, 1))
//        .assignReplicas(1, Arrays.asList(1, 2))
//        .assignReplicas(2, Arrays.asList(2, 0))
//        .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
//        .build();
//  }
}
