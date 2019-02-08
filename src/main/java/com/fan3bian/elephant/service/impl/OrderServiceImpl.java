package com.fan3bian.elephant.service.impl;

import com.fan3bian.elephant.domain.Order;
import com.fan3bian.elephant.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder(@Validated Order order) {

    }

    @Override
    public void updOrder(Order order) {

    }

    @Override
    public void delOrder(Order order) {

    }
}
