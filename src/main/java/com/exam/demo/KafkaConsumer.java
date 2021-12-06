package com.exam.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    ColourService colourService;

    @KafkaListener(topics="${kafka.topic_name}")
    public void listen(String message) {
        logger.info("Incoming: {}", message);
        colourService.save(new Colour(message));
    }

}
