package com.fan3bian.elephant.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Order {
    private String orderCode;
    private String buyer;
    private Date purchaseTime;
    private List<Item> items;

}
