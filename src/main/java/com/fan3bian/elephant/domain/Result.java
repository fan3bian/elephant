package com.fan3bian.elephant.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    boolean flag;
    String msg;
    T t;

}
