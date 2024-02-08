package com.omidmohebbise.springkafka.example1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SimpleKafkaConsumer {

    @KafkaListener(id = "myId", topics = KafkaConfiguration.EXAMPLE1_KAFKA_TOPIC)
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
