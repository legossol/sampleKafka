package kr.legossol.Kafka;

import org.apache.kafka.clients.producer.internals.FutureRecordMetadata;
import org.apache.kafka.clients.producer.internals.ProducerBatch;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.KafkaException;

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

}
