package com.kafka.producer.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DynamoDBUserRepoImpl {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public <S extends User> S save(S entity) {
        dynamoDBMapper.save(entity);
        return entity;
    }

    public Optional<User> findById(String id) {
        User user = dynamoDBMapper.load(User.class,id);
        return Optional.of(user);
    }

    public boolean existsById(String s) {
        User user = dynamoDBMapper.load(User.class, s);

        return user!=null;
    }

    public void deleteById(String s) {
        User user = dynamoDBMapper.load(User.class,s);
        dynamoDBMapper.delete(user);
    }

    public void delete(User entity) {
        dynamoDBMapper.delete(entity);
    }

}
