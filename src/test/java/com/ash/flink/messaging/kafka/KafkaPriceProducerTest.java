package com.ash.flink.messaging.kafka;

import com.ash.flink.domain.Price;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class KafkaPriceProducerTest {

    @Mock
    private Producer<String, Price> producer;

    @InjectMocks
    private KafkaPriceProducer kafka;

    @Test
    public void ensureKafkaSinkCallsSendOnProducerOnAddPrice() throws InterruptedException {
        final Price p = new Price("GB12345678", 0.25, 0.75, 0.5);
        kafka.addPrice(p);
        verify(producer).send(new ProducerRecord<>("prices", p));
    }

    @Test
    public void ensureKafkaSinkCallsCloseOnProducerOnClose() throws InterruptedException {
        kafka.close();
        verify(producer).close();
    }
}