# Spring Kafka Example Project

This project demonstrates the use of **Spring Kafka** for producing and consuming messages in a Kafka topic. It includes examples of both single message processing and batch message processing, with detailed handling of message acknowledgment.

## Features

- **Kafka Producer**: Sends messages to a Kafka topic at regular intervals.
- **Acknowledging Message Listener**: Processes individual Kafka messages with acknowledgment and retry logic.
- **Kafka Configuration**: Configures Kafka topics, consumer properties, and batch processing.

## Requirements

- **Java**: Version 21
- **Gradle**: Version 8.14
- **Spring Boot**: Version 3.4.6
- **Apache Kafka**: A running Kafka instance

## Project Structure

### Kafka Producer
The `ProducerEx2` class sends messages to the Kafka topic `example2-kafka-topic` every 3 seconds. It uses the `KafkaTemplate` to send messages asynchronously and logs metadata such as partition and timestamp.

### Acknowledging Message Listener
The `ConsumerEx2` class implements the `AcknowledgingMessageListener` interface to process individual Kafka messages. It uses the `Acknowledgment` object to manually commit or nack messages based on custom logic:
- **Acknowledgment**: Ensures that the message is marked as processed and committed.
- **Nack (Negative Acknowledgment)**: Allows retrying the message after a delay, useful for handling transient errors.

The listener processes messages differently based on the partition:
- Messages from partition `2` are retried once before being committed.
- Messages from other partitions are committed immediately.

### Kafka Configuration
The `ConfigurationEx2` class defines the Kafka topic, producer, and consumer properties. It also configures the acknowledgment mode for manual control.
