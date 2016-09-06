package com.ash.flink.messaging;

import com.ash.flink.domain.Price;

public interface PriceProducer extends AutoCloseable {

    void addPrice(Price p);

}
