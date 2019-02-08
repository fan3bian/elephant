package com.fan3bian.elephant.controller;

import com.fan3bian.elephant.domain.Order;
import com.fan3bian.elephant.domain.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/add")
    public Result addOrder(Order order){
        Result result = new Result();
        result.setFlag(true);
        result.setMsg("success");
        return result;
    }
    @RequestMapping("/update")
    public Result updOrder(Order order){
        return null;
    }

//
//    void delOrder(Order order);
}
