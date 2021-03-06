package com.kafka.producer.service;

import com.kafka.producer.Repository.DynamoDBUserRepoImpl;
import com.kafka.producer.Repository.UserRepositoryImpl;
import com.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    DynamoDBUserRepoImpl dynamoDBUserRepo;

    @Autowired
    UserRepositoryImpl repository;

    public User getUser(String userId) {
        User user = repository.get(userId);
        if(user == null) {
            Optional<User> userOpt = dynamoDBUserRepo.findById(userId);
            if(userOpt.isPresent()){
                user = userOpt.get();
                repository.save(user);
            }
        }
        return user;
    }

    public User put(User user) {
        if(!dynamoDBUserRepo.existsById(user.getId())) {
            dynamoDBUserRepo.save(user);
            return repository.save(user);
        }
        return user;
    }

    public void removeFromCache(String id) {
        repository.remove(id);
    }

    public String delete(String id) {
        String msg = "data not found for id-"+id;
        if(dynamoDBUserRepo.existsById(id)) {
            repository.remove(id);
            dynamoDBUserRepo.deleteById(id);
            msg = "removed successfully";
        }
        return msg;
    }
}
