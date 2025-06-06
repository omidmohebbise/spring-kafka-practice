package com.omidmohebbise.springkafka.example4;


import com.omidmohebbise.springkafka.example4.util.FileWriteUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static com.omidmohebbise.springkafka.example4.KStreamConfig.*;


@Service
@Slf4j
public class CarsConsumer {
    AtomicInteger count = new AtomicInteger(0);

    // ANSI escape codes for colors
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    @KafkaListener(topics = GENERAL_TERMINAL_PRINTER_TOPIC, groupId = "terminal-printer")
    public void generalConsumer(ConsumerRecord<String, String> record) {

        log.info("( "+count.getAndIncrement() + " ) General Consumed message: " + record.value() + ANSI_RESET);
    }

    @KafkaListener(topics = JP_TERMINAL_PRINTER_TOPIC, groupId = "terminal-printer")
    public void greenConsumer(ConsumerRecord<String, String> record) {
//        log.info(ANSI_GREEN + "Green Consumed message: " + record.value() + ANSI_RESET);
        FileWriteUtil.writeString("JP_CARS.txt", record.value());
    }

    @KafkaListener(topics = UK_TERMINAL_PRINTER_TOPIC, groupId = "terminal-printer")
    public void redConsumer(ConsumerRecord<String, String> record) {
//        log.info(ANSI_RED + "RED Consumed message: " + record.value() + ANSI_RESET);
        FileWriteUtil.writeString("UK_CARS.txt", record.value());
    }

    @KafkaListener(topics = US_TERMINAL_PRINTER_TOPIC, groupId = "terminal-printer")
    public void blueConsumer(ConsumerRecord<String, String> record) {
        //log.info(ANSI_BLUE + "BLUE Consumed message: " + record.value() + ANSI_RESET);
        FileWriteUtil.writeString("US_CARS.txt", record.value());
    }
}
