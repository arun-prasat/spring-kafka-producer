package com.kafka.producer.model;

public class Address {
    public int doorNo;
    public String street;
    public int pincode;

    public Address(int doorNo, String street, int pincode) {
        this.doorNo = doorNo;
        this.street = street;
        this.pincode = pincode;
    }
}
