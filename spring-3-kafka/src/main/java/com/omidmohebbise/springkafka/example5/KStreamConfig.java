package com.omidmohebbise.springkafka.example5;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafkaStreams
public class KStreamConfig {

    public static final String INPUT_CARS_TOPIC = "cars-topic";

    public static final String GENERAL_TERMINAL_PRINTER_TOPIC = "other-cars-topic";
    public static final String JP_TERMINAL_PRINTER_TOPIC = "jp-cars-topic";
    public static final String UK_TERMINAL_PRINTER_TOPIC = "uk-cars-topic";
    public static final String US_TERMINAL_PRINTER_TOPIC = "us-cars-topic";


    @Value(value = "${spring.kafka.streams.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.streams.application-id}")
    private String applicationId;

    @Bean
    public NewTopic generalTerminalPrinterTopic() {
        return TopicBuilder.name(GENERAL_TERMINAL_PRINTER_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic greenTerminalPrinterTopic() {
        return TopicBuilder.name(JP_TERMINAL_PRINTER_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic redTerminalPrinterTopic() {
        return TopicBuilder.name(UK_TERMINAL_PRINTER_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic blueTerminalPrinterTopic() {
        return TopicBuilder.name(US_TERMINAL_PRINTER_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic inputCarsTopic() {
        return TopicBuilder.name(INPUT_CARS_TOPIC)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, applicationId);
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return new KafkaStreamsConfiguration(props);
    }
}
