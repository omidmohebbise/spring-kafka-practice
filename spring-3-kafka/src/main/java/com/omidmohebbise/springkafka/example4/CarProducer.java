package com.omidmohebbise.springkafka.example4;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.omidmohebbise.springkafka.example4.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static com.omidmohebbise.springkafka.example4.KStreamConfig.INPUT_CARS_TOPIC;

@Service
@RequiredArgsConstructor
public class CarProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final JsonMapper jsonMapper;
    private final AtomicInteger counter = new AtomicInteger(0);

    public void produce(String brand, String model, String color, int year, int price) throws JsonProcessingException {
        var car = new Car(UUID.randomUUID().toString(), brand, model, color, year, price);
        CompletableFuture<SendResult<String, Object>> result = null;

        result = kafkaTemplate.send(INPUT_CARS_TOPIC, UUID.randomUUID().toString(), jsonMapper.writeValueAsString(car));

        result.whenComplete((success, failure) -> {
            if (failure != null) {
                failure.printStackTrace();
            }
        });
    }

    @Scheduled(fixedRate = 10)
    public void produce() throws JsonProcessingException {
        if (counter.getAndIncrement() <= 100) {
            String[] carBrands = {"Toyota", "Range Rover", "Ford"};
            String[] carModels = {"Camry", "Land Rover", "Focus"};
            String[] carColors = {"White", "Black", "Silver", "Red", "Blue"};
            int[] carYears = {2015, 2018, 2020, 2022, 2023};
            int[] carPrices = {15000, 20000, 25000, 30000, 35000};

            Random random = new Random();

            // Generate random index for each attribute array

            int modelIndex = random.nextInt(carBrands.length);
            int colorIndex = random.nextInt(carColors.length);
            int yearIndex = random.nextInt(carYears.length);
            int priceIndex = random.nextInt(carPrices.length);

            // Create a new car record using the randomly selected attributes
            produce(carBrands[modelIndex], carModels[modelIndex], carColors[colorIndex],
                    carYears[yearIndex], carPrices[priceIndex]);
        }


    }

}
