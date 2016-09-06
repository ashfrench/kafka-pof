package com.ash.flink.domain.serializer;

import com.ash.flink.domain.Price;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class PriceSerializer implements Serializer<Price>, Deserializer<Price>{
    /**
    * Configure this class.
    *
    * @param configs configs in key/value pairs
    * @param isKey   whether is for key or value
    */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Price deserialize(String topic, byte[] bytes) {
        String s = new String(bytes);
        System.out.println("Deserialized: " + s);
        String[] split = s.split("\\|");

        return new Price(split[0],
                Double.parseDouble(split[1]),
                Double.parseDouble(split[2]),
                Double.parseDouble(split[3]));
    }

    /**
    * @param topic topic associated with data
    * @param data  typed data
    * @return serialized bytes
    */
    @Override
    public byte[] serialize(String topic, Price data) {
        return (data.isin + '|' + data.bid + '|' + data.ask + '|' + data.mid).getBytes();
    }

    /**
    * Close this serializer.
    * This method has to be idempotent if the serializer is used in KafkaProducer because it might be called
    * multiple times.
    */
    @Override
    public void close() {

    }
}
