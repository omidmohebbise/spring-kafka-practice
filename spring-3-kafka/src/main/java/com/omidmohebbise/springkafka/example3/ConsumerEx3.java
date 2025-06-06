package com.omidmohebbise.springkafka.example3;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.omidmohebbise.springkafka.example3.ConfigurationEx3.EXAMPLE3_GROUP_ID;
import static com.omidmohebbise.springkafka.example3.ConfigurationEx3.EXAMPLE3_KAFKA_TOPIC;

@Service
@Log4j2
@RequiredArgsConstructor
public class ConsumerEx3 {

    @KafkaListener(topics = EXAMPLE3_KAFKA_TOPIC, groupId = EXAMPLE3_GROUP_ID)
    public void consumeBatchOfMessages(List<String> messages) throws InterruptedException {
        log.info("Got [{}] messages : {}", messages.size(), messages);
        Thread.sleep(5000);
    }
}
