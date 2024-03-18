package com.omidmohebbise.springkafka.example3;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

import static com.omidmohebbise.springkafka.example3.KafkaConfiguration.EXAMPLE3_KAFKA_TOPIC;

@Service
@RequiredArgsConstructor
public class SimpleKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Scheduled(fixedRate = 1000) // Send a message every 5 seconds
    public void produceMessage() {
        var time = LocalTime.now();
        kafkaTemplate.send(EXAMPLE3_KAFKA_TOPIC, String.format("Example3, %d %d %d", time.getHour(), time.getMinute(), time.getSecond()));
    }
}
