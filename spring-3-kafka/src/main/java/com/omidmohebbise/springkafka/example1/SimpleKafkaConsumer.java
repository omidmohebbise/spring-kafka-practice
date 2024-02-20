package com.omidmohebbise.springkafka.example1;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleKafkaConsumer {

    @KafkaListener(id = "myId", topics = KafkaConfiguration.EXAMPLE1_KAFKA_TOPIC)
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
