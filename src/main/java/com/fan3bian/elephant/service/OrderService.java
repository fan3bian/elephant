package com.fan3bian.elephant.service;

import com.fan3bian.elephant.domain.Order;

public interface OrderService {

    void addOrder(Order order);

    void updOrder(Order order);

    void delOrder(Order order);
}
