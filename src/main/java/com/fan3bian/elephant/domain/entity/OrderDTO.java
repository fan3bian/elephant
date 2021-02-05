package com.fan3bian.elephant.domain.entity;

import lombok.Data;

@Data
public class OrderDTO {
  String customerFirstName;
  String customerLastName;
  String billingStreet;
  String billingCity;
}