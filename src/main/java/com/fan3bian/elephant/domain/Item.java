package com.fan3bian.elephant.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Item {
    @NotNull
    private Integer orderLine;
    @NotBlank
    private String itemCode;
    private String itemName;
    private Integer qty;
}
