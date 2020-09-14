package com.kafka.producer.Repository;

import com.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private RedisTemplate<String,User> redisTemplate;
    private HashOperations hashOperations;

    UserRepositoryImpl(RedisTemplate<String,User> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public User save(User user) {
        hashOperations.put("User",user.id, user);
        return (User) hashOperations.get("User",user.id);
    }

    @Override
    public User get(String id) {
        return (User) hashOperations.get("User",id);
    }

    public void remove(String id) {
        hashOperations.delete("User", id);
    }
}
