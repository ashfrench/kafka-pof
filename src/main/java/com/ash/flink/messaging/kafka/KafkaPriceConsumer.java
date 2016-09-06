package com.ash.flink.messaging.kafka;

import com.ash.flink.domain.Price;
import com.ash.flink.domain.serializer.PriceSerializer;
import com.ash.flink.messaging.PriceConsumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class KafkaPriceConsumer implements PriceConsumer {

    private final Consumer<String, Price> consumer;

    public KafkaPriceConsumer(){
        this(new KafkaConsumer<>(defaultKafkaProperties(), new StringDeserializer(), new PriceSerializer()));
    }

    public KafkaPriceConsumer(Consumer<String, Price> consumer){
        this.consumer = consumer;
        consumer.subscribe(Collections.singletonList(KafkaPriceProducer.TOPIC));
    }

    @Override
    public List<Price> getPrices() {
        ConsumerRecords<String, Price> consumerRecords = consumer.poll(100);
        List<Price> prices = new ArrayList<>();
        for(ConsumerRecord<String, Price> record : consumerRecords){
            prices.add(record.value());
        }

        return prices;
    }

    private static Properties defaultKafkaProperties() {
        final Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.block.ms", 1000);

        return props;
    }
}
