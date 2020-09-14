package com.kafka.producer.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class Address {
    public int doorNo;
    public String street;
    public int pincode;

    @DynamoDBAttribute
    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    @DynamoDBAttribute
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @DynamoDBAttribute
    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public Address(int doorNo, String street, int pincode) {
        this.doorNo = doorNo;
        this.street = street;
        this.pincode = pincode;
    }

    public Address() {}

}
