package com.omidmohebbise.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
        scanBasePackages = {
//                "com.omidmohebbise.springkafka.example1",
                "com.omidmohebbise.springkafka.example2"
        }
)
@EnableScheduling
public class SpringKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }


}
