package com.kafka.cosumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import javax.swing.plaf.PanelUI;


@Configuration
public class KafkaConsumerListener {

    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    // No tengo grupo. He puesto ese porque me pide uno.
    @KafkaListener(topics = {"jose-1-topic"} , groupId = "my-group-id")
    public void listener (String message) {
        LOGGER.info("Mensaje recibido: {}" , message);
    }
}
