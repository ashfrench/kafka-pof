package com.ash.flink;

import com.ash.flink.domain.PriceFactory;
import com.ash.flink.messaging.kafka.KafkaPriceProducer;
import com.ash.flink.utils.PricePublisher;
import com.ash.flink.utils.PricerConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerMain {

    public static void main(String[] args) throws IOException {
        if(args.length != 1){
            throw new IllegalArgumentException("no file specified");
        }
        final String filename = args[0];
        final PricerConfig config = new PricerConfig();
        final List<PriceFactory> priceFactories = config.getPriceFactories(new File(filename));
        final KafkaPriceProducer priceProducer = new KafkaPriceProducer();

        final ExecutorService service = Executors.newFixedThreadPool(priceFactories.size());

        for (PriceFactory priceFactory : priceFactories) {
            final PricePublisher pricePublisher = new PricePublisher(priceProducer, priceFactory);
            service.submit(() -> {
                while(true){
                    pricePublisher.publishPriceForRandomInstrument();
                }
            });
        }
    }

}
