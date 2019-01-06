package com.fan3bian.elephant.service.impl;

import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public Result<String> echo(String str) {
        Result<String> result = new Result<>();
        result.setFlag(true);
        return result;
    }
}
