package com.omidmohebbise.springkafka.example4.processor;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

import static com.omidmohebbise.springkafka.example4.KStreamConfig.*;

@Slf4j
@Component
public class SimpleCarProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private final StreamsBuilder streamsBuilder;

    public SimpleCarProcessor(StreamsBuilder streamsBuilder) {
        this.streamsBuilder = streamsBuilder;
        buildPipeline();
    }

    void buildPipeline() {
        KStream<String, String> messageStream = streamsBuilder
                .stream(INPUT_CARS_TOPIC, Consumed.with(STRING_SERDE, STRING_SERDE));

        messageStream.filter((key, value) -> value.contains("Toyota"))
                .map((key, value) -> new KeyValue<>(key, value.toUpperCase()))
                .to(JP_TERMINAL_PRINTER_TOPIC, Produced.with(STRING_SERDE, STRING_SERDE));

        messageStream.filter((key, value) -> value.contains("Range Rover"))
                .map((key, value) -> new KeyValue<>(key, value.toUpperCase()))
                .to(UK_TERMINAL_PRINTER_TOPIC, Produced.with(STRING_SERDE, STRING_SERDE));

        messageStream.filter((key, value) -> value.contains("Ford"))
                .map((key, value) -> new KeyValue<>(key, value.toUpperCase()))
                .to(US_TERMINAL_PRINTER_TOPIC, Produced.with(STRING_SERDE, STRING_SERDE));
    }
}
