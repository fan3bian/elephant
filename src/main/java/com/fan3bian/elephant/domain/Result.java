package com.fan3bian.elephant.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Result<T> {
    boolean flag;
    String msg;
    T t;

    public Result(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }
}
