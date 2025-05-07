package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic () {

        Map<String,String> configurations = new HashMap<>();
        // delete (borra el mensaje)
        // compact (Mantiene el mas actual)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG , TopicConfig.CLEANUP_POLICY_DELETE );
        configurations.put(TopicConfig.RETENTION_MS_CONFIG , "86400000"); // tiempo de retencion de mensajes. por defecto es -1 (no se borran)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG , "1073741824" ); // TAMAÑO MAXIMO DEL SEGMENTO POR DEFECTO ES UN 1gb
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG , "100000" ); // TAMAÑO MAXIMO DE CADA MENSAJE. POR DEFECTO ES 1MB


        return TopicBuilder.name("jose-1-topic")
                .partitions(1)
                .replicas(1)
                .configs(configurations)
                .build();
    }
}
