package com.omidmohebbise.springkafka.example3;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.omidmohebbise.springkafka.example3.KafkaConfiguration.EXAMPLE3_GROUP_ID;
import static com.omidmohebbise.springkafka.example3.KafkaConfiguration.EXAMPLE3_KAFKA_TOPIC;

@Service
@Log4j2
@RequiredArgsConstructor
public class BatchMessageListenerImpl  {

    @KafkaListener(topics = EXAMPLE3_KAFKA_TOPIC, groupId = EXAMPLE3_GROUP_ID)
    public void consumeBatchOfMessages(List<String> messages) throws InterruptedException {
        log.info("Got messages : {}", messages);
        Thread.sleep(5000);
    }
}
