package com.omidmohebbise.springkafka.example2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.IntStream;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerEx2 {

    private final KafkaTemplate<String, String> kafkaTemplate;

    Queue<Integer> requests = new ConcurrentLinkedQueue<>(IntStream.range(0, 100).boxed().toList());


    @Scheduled(fixedRate = 3000)
    public void produce() throws ExecutionException, InterruptedException {
        if (!requests.isEmpty()) {
            String message = String.format("Ex2: Producer (msg:%d), time: %s ", requests.poll(), LocalTime.now());
            CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(ConfigurationEx2.EXAMPLE2_KAFKA_TOPIC,
                    String.valueOf(ThreadLocalRandom.current().nextInt(1000)), message);

            RecordMetadata metaData;

            metaData = result.get().getRecordMetadata();

            log.atInfo()
                    .addArgument(metaData.partition())
                    .addArgument(metaData.timestamp())
                    .addArgument(message)
                    .log("partition: [{}] ( timestamp : [{}]) => {}");
        }
    }
}
