package com.omidmohebbise.springkafka.example4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.omidmohebbise.springkafka.example4.ConfigurationEx4.EXAMPLE4_GROUP_ID;
import static com.omidmohebbise.springkafka.example4.ConfigurationEx4.EXAMPLE4_KAFKA_TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerEx4 {
    private final List<String> messageBuffer = new ArrayList<>();
    private final int MAX_MESSAGES = 10;
    private final AtomicInteger messageCount = new AtomicInteger(0);

    @KafkaListener(topics = EXAMPLE4_KAFKA_TOPIC, groupId = EXAMPLE4_GROUP_ID)
    public void listen(List<String> records) {
        synchronized (messageBuffer) {
            messageBuffer.addAll(records);
            log.info("Buffer Size: {}", messageBuffer.size());
            if (messageBuffer.size() >= MAX_MESSAGES) {
                processMessages();
            }
        }
    }

    @Scheduled(fixedRate = 30000) // Runs every 0.5 minute
    public void processScheduledMessages() {
        synchronized (messageBuffer) {
            if (!messageBuffer.isEmpty()) {
                processMessages();
            }
        }
    }
    @Scheduled(fixedRate = 1000) // Runs every 0.5 minute
    public void timer() {
        messageCount.getAndIncrement();
    }

    private void processMessages() {
        // Handle the messages in the buffer

        log.atInfo()
                .addArgument(messageCount)
                .addArgument(messageBuffer.size())
                .addArgument(messageBuffer)
                .log("Processing messages[{}s][{}]: {}");
        messageBuffer.clear(); // Clear the buffer after processing
        messageCount.set(0);
    }

}
