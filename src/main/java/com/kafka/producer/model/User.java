package com.kafka.producer.model;

public class User {
    public String name;
    public int age;
    public Address address;

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

}
