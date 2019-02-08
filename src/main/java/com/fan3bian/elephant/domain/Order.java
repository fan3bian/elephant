package com.fan3bian.elephant.domain;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    @NotBlank
    private String orderCode;
    private String buyer;
    @Past
    private Date purchaseTime;
    private List<Item> items;

}
