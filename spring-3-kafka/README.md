# Spring-Kafka Examples

In this project, I implement common Kafka use cases related to Spring applications.

## Setup Kafka with single node

To run Kafka with a single node, you only need to execute the following Docker command and run the `kafka-single-node.yml` file:

```bash
docker compose up -f kafka-single-node.yml up -d

```

## Setup Kafka with multi nodes

To run the Kafka with a single node you need only run below docker command and run the kafka-multi-node.yml


docker compose up -f kafka-multi-node.yml up -d


## Example 1: Simple producer/consumer with Spring boot

The example includes three files to configure Kafka producer and consumer and publish a string as a message and receive it in the consumer listener.


## Example 2: Use ack in consumer

## Example 3: Use batch messages in consumer

In this example, I configured the consumer to poll a maximum of 5 messages (max.poll.records=5,000).

So, every second, the producer sends a message, but the consumer processes the messages only when 5 items are ready in the topic.

## Example 4: Add Exception Handler
