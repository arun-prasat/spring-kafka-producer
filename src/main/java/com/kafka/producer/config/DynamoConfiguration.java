package com.kafka.producer.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.util.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoConfiguration {
    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoEndPoint;
    @Value("${amazon.dynamodb.accesskey}")
    private String awsAccessKey;
    @Value("${amazon.dynamodb.secretkey}")
    private String awsSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAwsCredentials());
        if(!StringUtils.isNullOrEmpty(dynamoEndPoint)) {
            amazonDynamoDB.setEndpoint(dynamoEndPoint);
        }

        return  amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAwsCredentials() {
        return new BasicAWSCredentials(awsAccessKey,awsSecretKey);
    }
}
