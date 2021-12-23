package kr.legossol.Kafka.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

public class ForAssignProduceCustomPartition implements Partitioner  {

  private final Set<String> customPartition;

  public ForAssignProduceCustomPartition() {
    customPartition = new HashSet<>();
  }

  @Override
  public int partition(String topic,
      Object key,
      byte[] keyBytes,
      Object value,
      byte[] valueBytes,
      Cluster cluster) {
    final List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
    final int partitionCount = partitionInfos.size();
    final int specificPartition = partitionCount - 1;

    final String stringKey = (String) key;

//    if((keyBytes == null)){
//      throw new IllegalArgumentException("토픽이 스트링이 아니거나 keyByte가 null입니다.");
//    }
//    if((key).equals(topic)){
//      return numPartitions - 1;
//    }
    //이미 특수한 partition의 key값을가지고 있다면 specificPartition(size의 -1)을 리턴
    if (customPartition.contains(stringKey)) {
      return specificPartition;
    } else {
      //없다면 string의 hash값을 specificPartition(size의 -1)로 나눈 값을 리턴
      return (Math.abs(stringKey.hashCode()) % (specificPartition));
    }
  }

  @Override
  public void close() {

  }

  @Override
  public void onNewBatch(String topic, Cluster cluster, int prevPartition) {
    Partitioner.super.onNewBatch(topic, cluster, prevPartition);
  }

  @Override
  public void configure(Map<String, ?> configs) {
//    final String specificPartitionStr = (String) configs.get("specificPartition");
//    customPartition.addAll(Arrays.asList(specificPartitionStr.split(",")));

  }
}
