package kr.legossol.Kafka.topic;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.TopicListing;

@Slf4j
public class ListenerTopics {

  public void showTopicInfo() throws ExecutionException, InterruptedException {
    Properties topicConfig = new Properties();
    topicConfig.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    AdminClient admin = AdminClient.create(topicConfig);

    for(TopicListing topicListing : admin.listTopics().listings().get()){
      log.info(String.valueOf(topicListing));
    }
  }
}
