package com.omidmohebbise.springkafka.example2;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AcknowledgingMessageListenerImpl implements AcknowledgingMessageListener<String, String> {

    private final Set<String> failedList = new HashSet<>();

    @Override
    @KafkaListener(id = "myId", topics = KafkaConfiguration.EXAMPLE2_KAFKA_TOPIC, groupId = "example2-kafka-group")
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {

        System.out.print(consumerRecord.value() + "\t ");
        if (consumerRecord.partition() != 2) {
            acknowledgment.acknowledge();
            System.out.println(" committed");
        } else {
            if (failedList.contains(consumerRecord.key())) {
                failedList.remove(consumerRecord.key());
                System.out.println(consumerRecord.partition() + ": " + consumerRecord.key() + " processed again");
                acknowledgment.acknowledge();

            } else {
                failedList.add(consumerRecord.key());
                acknowledgment.nack(Duration.ofSeconds(1));
                System.out.println(consumerRecord.partition() + ": " + consumerRecord.key() + " not committed");
            }
        }
    }
}
