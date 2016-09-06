package com.ash.flink.domain.serializer;

import com.ash.flink.domain.Price;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriceSerializerTest {


    PriceSerializer serializer = new PriceSerializer();

    @Test
    public void serialize() throws Exception {
        Price price = new Price("ABC123", 1.2, 2.4, 2.6);
        byte[] bytes = serializer.serialize("topic", price);

        Price deserialize = serializer.deserialize("topic", bytes);
        assertEquals(price, deserialize);
    }

}