package com.omidmohebbise.springkafka.example4;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static com.omidmohebbise.springkafka.example4.ConfigurationEx4.EXAMPLE4_KAFKA_TOPIC;

@Service
@RequiredArgsConstructor
public class ProducerEx4 {
    public static final String EX_4_PRODUCER_MESSAGE_ID = "Ex4: Producer, messageId [%s] ";
    private final AtomicInteger counter = new AtomicInteger(0);

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Scheduled(fixedRate = 1000)
    public void produceMessage() {
        String message = String.format(EX_4_PRODUCER_MESSAGE_ID, counter.incrementAndGet()) ;
        kafkaTemplate.send(EXAMPLE4_KAFKA_TOPIC,message);
    }

    @Scheduled(fixedRate = 1000)
    public void produceMessage2() {
        String message = String.format(EX_4_PRODUCER_MESSAGE_ID, counter.incrementAndGet()) ;
        kafkaTemplate.send(EXAMPLE4_KAFKA_TOPIC,message);
    }
}
