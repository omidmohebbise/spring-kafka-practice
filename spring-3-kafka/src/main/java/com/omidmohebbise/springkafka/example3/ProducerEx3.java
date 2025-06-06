package com.omidmohebbise.springkafka.example3;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static com.omidmohebbise.springkafka.example3.ConfigurationEx3.EXAMPLE3_KAFKA_TOPIC;

@Service
@RequiredArgsConstructor
public class ProducerEx3 {
    public static final String EX_3_PRODUCER_MESSAGE_ID = "Ex3: Producer, messageId [%s] ";
    private final AtomicInteger counter = new AtomicInteger(0);

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Scheduled(fixedRate = 1000)
    public void produceMessage() {
        String message = String.format(EX_3_PRODUCER_MESSAGE_ID, counter.incrementAndGet()) ;
        kafkaTemplate.send(EXAMPLE3_KAFKA_TOPIC,message);
    }
}
