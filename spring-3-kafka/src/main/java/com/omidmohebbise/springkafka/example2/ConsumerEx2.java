package com.omidmohebbise.springkafka.example2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerEx2 implements AcknowledgingMessageListener<String, String> {

    private final Set<String> failedList = new HashSet<>();

    @Override
    @KafkaListener(id = "myId", topics = ConfigurationEx2.EXAMPLE2_KAFKA_TOPIC, groupId = "example2-kafka-group")
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        String statusMessage;

        if (consumerRecord.partition() != 2) {
            acknowledgment.acknowledge();
            statusMessage = " committed";
        } else {
            if (failedList.contains(consumerRecord.key())) {
                failedList.remove(consumerRecord.key());
                statusMessage = consumerRecord.partition() + ": " + consumerRecord.key() + " processed again";
                acknowledgment.acknowledge();

            } else {
                failedList.add(consumerRecord.key());
                acknowledgment.nack(Duration.ofSeconds(1));
                statusMessage = consumerRecord.partition() + ": " + consumerRecord.key() + " not committed";
            }
        }
        log.info("Consumer Record: [{}] : {}", consumerRecord.value(), statusMessage);
    }
}
