package com.ash.flink.messaging;

import com.ash.flink.domain.Price;

import java.util.List;

public interface PriceConsumer {

    List<Price> getPrices();

}
