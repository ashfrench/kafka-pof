package com.ash.flink.utils;

import com.ash.flink.domain.Price;
import com.ash.flink.domain.PriceFactory;
import com.ash.flink.messaging.PriceProducer;

import java.time.LocalTime;
import java.util.Random;

/**
 * Created by jbowkett on 31/03/2016.
 */
public class PricePublisher {
  private final PriceProducer priceProducer;
  private final PriceFactory[] priceFactories;
  private final Random randomIndex = new Random();

  public PricePublisher(PriceProducer priceProducer, PriceFactory... priceFactories) {
    this.priceProducer = priceProducer;
    this.priceFactories = priceFactories;
  }

  public void publishPriceForRandomInstrument() {
    final Price price = priceFactories[randomIndex()].next();
    System.out.println("price = " + price + " " + LocalTime.now());
    priceProducer.addPrice(price);
  }

  private int randomIndex() {
    return (int)(randomIndex.nextDouble() * priceFactories.length);
  }
}
