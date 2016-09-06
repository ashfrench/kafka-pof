package com.ash.flink;

import com.ash.flink.domain.Price;
import com.ash.flink.messaging.kafka.KafkaPriceConsumer;

import java.util.List;

public class ConsumerMain {

    public static void main(String[] args) {
        KafkaPriceConsumer consumer = new KafkaPriceConsumer();
        while(true){
            List<Price> prices = consumer.getPrices();
            System.out.println();
            System.out.println("GOT PRICES");
            System.out.println(prices);
        }
    }
}
