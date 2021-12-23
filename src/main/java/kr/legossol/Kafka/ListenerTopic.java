package kr.legossol.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ListenerTopic {
//    public void showTopicInfo() throws ExecutionException, InterruptedException {
//        Properties topicConfig = new Properties();
//        topicConfig.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        AdminClient admin = AdminClient.create(topicConfig);
//        for(TopicListing topicListing : admin.listTopics().listings().get()){
//            log.info(String.valueOf(topicListing));
//        }
//    }
}
