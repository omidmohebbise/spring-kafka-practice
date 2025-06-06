# Spring Kafka Example Project

This project demonstrates the use of **Spring Kafka** for producing and consuming messages in a Kafka topic. It includes examples of both single message processing and batch message processing.

## Features

- **Kafka Producer**: Sends messages to a Kafka topic at regular intervals.
- **Batch Message Listener**: Processes a batch of Kafka messages.
- **Kafka Configuration**: Configures Kafka topics, consumer properties, and batch processing.

## Requirements

- **Java**: Version 21
- **Gradle**: Version 8.14
- **Spring Boot**: Version 3.4.6
- **Apache Kafka**: A running Kafka instance

## Project Structure

### Kafka Producer
The `SimpleKafkaProducer` class sends messages to the Kafka topic `example3-kafka-batch-consumer-topic` every second.

### Batch Message Listener
The `BatchMessageListenerImpl` class processes a batch of Kafka messages and logs them.

### Kafka Configuration
The `KafkaConfiguration` class defines the Kafka topic, consumer properties, and enables batch processing.
