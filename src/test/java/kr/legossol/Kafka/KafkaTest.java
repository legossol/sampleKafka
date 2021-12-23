package kr.legossol.Kafka;

import kr.legossol.Kafka.messageDto.Message;
import kr.legossol.Kafka.service.AssignPartitionProducer;
import kr.legossol.Kafka.service.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class KafkaTest {

//  @Test
//  public void testBatchCannotAbortTwice() throws Exception {
//    ProducerBatch batch = new ProducerBatch(new TopicPartition("topic", 1), memoryRecordsBuilder, now);
//    MockCallback callback = new MockCallback();
//    FutureRecordMetadata future = batch.tryAppend(now, null, new byte[10], Record.EMPTY_HEADERS, callback, now);
//    KafkaException exception = new KafkaException();
//    batch.abort(exception);
//    assertEquals(1, callback.invocations);
//    assertEquals(exception, callback.exception);
//    assertNull(callback.metadata);
//    try {
//      batch.abort(new KafkaException());
//      fail("Expected exception from abort");
//    } catch (IllegalStateException e) {
//      // expected
//    }
//    assertEquals(1, callback.invocations);
//    assertTrue(future.isDone());
//    try {
//      future.get();
//      fail("Future should have thrown");
//    } catch (ExecutionException e) {
//      assertEquals(exception, e.getCause());
//    }
//  }
  @Autowired
  private AssignPartitionProducer producer;



  @Test
  public void Test() throws InterruptedException {
    Message message = new Message();
        message.setAuthor("테스트 진행자");
    message.setContent(
        "나야나"
//            + "두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두"
//            + "루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhh두루두루hhhdurl여기까지왔어 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 "
//            + "이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 "
//            + "왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 "
//            + "진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 "
//            + "이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜"
//            + " 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 "
//            + "왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 "
//            + "이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 "
//            + "진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼"
//            + " 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜"
//            + " 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어"
//            + " 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜 내가 이만큼 왔어 진짜 이만큼 왔어 진짜 잘했지 진짜"
    );

//    for(int i = 5; i < 25; i++){
//      message.setCnt(i);
      producer.partitionSend(message);
//      Thread.sleep(4000);
//    }
  }

}
