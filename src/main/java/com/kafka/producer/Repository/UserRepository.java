package com.kafka.producer.Repository;

import com.kafka.producer.model.User;

public interface UserRepository {
    User save (User user);
    User get(String id);
}
