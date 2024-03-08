package com.omidmohebbise.springkafka.example2;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;


@Service
@Log4j2
@RequiredArgsConstructor
public class SimpleKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Marker MARKER_IMPORTANT = MarkerManager.getMarker("IMPORTANT");

    Queue<Integer> requests = new ConcurrentLinkedQueue<>(IntStream.range(0, 100).boxed().toList());

    public void sendMessage(String message) {

        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(KafkaConfiguration.EXAMPLE2_KAFKA_TOPIC,
                String.valueOf((new Random()).nextInt(1000))
                , message);
        RecordMetadata metaData;
        try {
            metaData = result.get().getRecordMetadata();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info(MARKER_IMPORTANT, String.format("partition: %d ( timestamp : %s) \n", metaData.partition(), metaData.timestamp()));

    }

    @Scheduled(fixedRate = 1000)
    public void produce() {
        if (!requests.isEmpty())
            sendMessage(String.format("Example2 ( request %d), time: %s ", requests.poll(), LocalTime.now()));
    }
}
