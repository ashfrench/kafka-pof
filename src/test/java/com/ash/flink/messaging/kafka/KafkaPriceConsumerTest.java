package com.ash.flink.messaging.kafka;

import com.ash.flink.domain.Price;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KafkaPriceConsumerTest {

    private Consumer<String, Price> consumer;

    @Before
    public void setUp(){
        consumer = mock(Consumer.class);
        ConsumerRecords consumerRecords = mock(ConsumerRecords.class);

        ConsumerRecord<String, Price> consumerRecord1 = new ConsumerRecord<>("", 1, 1, "Key", new Price("ABC123", 1.4, 2.5, 2.6));
        ConsumerRecord<String, Price> consumerRecord2 = new ConsumerRecord<>("", 1, 1, "Key", new Price("DEF123", 1.4, 2.5, 2.6));

        when(consumerRecords.iterator()).thenReturn(Arrays.asList(consumerRecord1, consumerRecord2).iterator());
        when(consumer.poll(anyLong())).thenReturn(consumerRecords);
    }

    @Test
    public void testConsumer(){
        KafkaPriceConsumer kafkaPriceConsumer = new KafkaPriceConsumer(consumer);
        List<Price> prices = kafkaPriceConsumer.getPrices();
        assertEquals(Arrays.asList(new Price("ABC123", 1.4, 2.5, 2.6), new Price("DEF123", 1.4, 2.5, 2.6)), prices);
    }

}