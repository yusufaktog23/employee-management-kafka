package com.aktog.yusuf.employeemanagementlombok.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String kafkaHost;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String kafkaConsumerKeyDeserializer;
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String kafkaConsumerValueDeserializer;


    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerKeyDeserializer);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerValueDeserializer);
        props.put(MAX_POLL_INTERVAL_MS_CONFIG, 300000);
        props.put(MAX_POLL_RECORDS_CONFIG, 10);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(10);
        return factory;
    }
}
