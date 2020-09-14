package com.kafka.producer.controller;

import com.kafka.producer.model.Address;
import com.kafka.producer.model.User;
import com.kafka.producer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class UserController {

    @Autowired
    KafkaTemplate<String,User> kafkaTemplate;

    @Autowired
    UserService userService;

    @GetMapping("/health")
    public String getStatus() {
        return "success";
    }

    @GetMapping("/publish/user/{name}")
    public String postUser(@PathVariable("name") final String name) {
        User user = new User("1", name, 21,new Address(10,"peelamedu",641004));
        kafkaTemplate.send("kafka_example_user",user);
        return "published successfully";
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUser(@PathVariable final String userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        User us = userService.put(user);
        return new ResponseEntity<>(us, HttpStatus.CREATED);
    }

    @DeleteMapping("/removeCache/{id}")
    public ResponseEntity<String> removeCache(@PathVariable String id) {
        userService.removeFromCache(id);
        return new ResponseEntity<>("deleted Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        String msg = userService.delete(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
