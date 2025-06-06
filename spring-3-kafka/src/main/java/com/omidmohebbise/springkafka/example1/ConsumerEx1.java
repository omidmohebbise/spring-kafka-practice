package com.omidmohebbise.springkafka.example1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerEx1 {

    @KafkaListener(id = "myId", topics = ConfigurationEx1.EXAMPLE1_KAFKA_TOPIC)
    public void consume(String message) {
        log.atInfo().addArgument(message).log("Consumed message: {}");
    }
}
