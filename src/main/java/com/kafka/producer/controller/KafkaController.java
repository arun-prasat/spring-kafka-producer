package com.kafka.producer.controller;

import com.kafka.producer.model.Address;
import com.kafka.producer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    @Autowired
    KafkaTemplate<String,User> kafkaTemplate;

    @GetMapping("/health")
    public String getStatus() {
        return "success";
    }

    @GetMapping("/publish/user/{name}")
    public String postUser(@PathVariable("name") final String name) {
        User user = new User(name, 21,new Address(10,"peelamedu",641004));
        kafkaTemplate.send("kafka_example_user",user);
        return "published successfully";
    }
}
