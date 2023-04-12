package com.aktog.yusuf.employeemanagementlombok.service.kafka;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
@AllArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public CompletableFuture<SendResult<String, String>> produce(String topic, Object message) {

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, gson.toJson(message));

        future.whenComplete((result, ex) -> {
            try {
                log.info("Sent message = ' {} '  with offset = ' {} ' on partition = ' {} '",
                        message, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            } catch (Exception e) {
                log.error("Unable to send message = ' {} ' due to : {}", message, ex.getMessage());

            }
        });
        return future;
    }


}

