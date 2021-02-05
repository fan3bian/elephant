package com.fan3bian.elephant.domain.entity;

import lombok.Data;

@Data
public class Order {
    Customer customer;
    Address billingAddress;
}

@Data
class Customer {
    Name name;
}

@Data
class Name {
    String firstName;
    String lastName;
}

@Data
class Address {
    String street;
    String city;
}