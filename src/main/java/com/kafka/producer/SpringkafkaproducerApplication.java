package com.kafka.producer;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class SpringkafkaproducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringkafkaproducerApplication.class, args);
	}

}
