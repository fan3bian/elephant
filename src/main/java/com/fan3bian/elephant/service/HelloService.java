package com.fan3bian.elephant.service;

import com.fan3bian.elephant.domain.Result;

public interface HelloService {
    Result<String> echo(String str);
}
