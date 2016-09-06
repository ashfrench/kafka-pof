package com.ash.flink.messaging.kafka;

import com.ash.flink.domain.Price;
import com.ash.flink.domain.serializer.PriceSerializer;
import com.ash.flink.messaging.PriceProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaPriceProducer implements PriceProducer{

    private final Producer<String, Price> producer;

    public static final String TOPIC = "prices";

    public KafkaPriceProducer(){
        this(new KafkaProducer<>(defaultKafkaProperties(), new StringSerializer(), new PriceSerializer()));
    }

    public KafkaPriceProducer(Producer<String, Price> producer){
        this.producer = producer;
    }

    @Override
    public void addPrice(Price p) {
        producer.send(new ProducerRecord<>(TOPIC, p));
        producer.flush();
    }

    @Override
    public void close() {
        producer.close();
    }

    private static Properties defaultKafkaProperties() {
        final Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("max.block.ms", 10000);

        return props;
    }
}
