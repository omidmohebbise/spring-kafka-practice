package com.omidmohebbise.springkafka.example1;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SimpleKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(KafkaConfiguration.EXAMPLE1_KAFKA_TOPIC, message);
    }

    @Scheduled(fixedRate = 1000)
    public void produce() {
        sendMessage("Hello Kafka " + LocalDateTime.now());
    }
}
