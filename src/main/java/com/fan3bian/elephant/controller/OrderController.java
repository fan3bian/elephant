package com.fan3bian.elephant.controller;

import com.fan3bian.elephant.domain.Item;
import com.fan3bian.elephant.domain.Order;
import com.fan3bian.elephant.domain.Result;
import com.fan3bian.elephant.domain.condition.ReportMasterCondition;
import com.fan3bian.elephant.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @RequestMapping("/add")
    public Result addOrder(@RequestBody Order order, HttpServletRequest request, String hello) {
        log.error(JsonUtil.toJson(order));
        List<Item> items = order.getItems();
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item next = iterator.next();
            if (next.getQty() != null && next.getQty().equals(0)) {
                iterator.remove();
            }
        }
        log.error(JsonUtil.toJson(order));
        Result result = new Result();
        result.setFlag(true);
        result.setMsg("success");
        return result;
    }

    @RequestMapping("/update")
    public Result updOrder(Order order) {
        return null;
    }

//
//    void delOrder(Order order);

    @GetMapping(value = "/report")
    public void query(@Valid ReportMasterCondition cond) {
        System.out.println(JsonUtil.toJson(cond));
    }

    @PostMapping(value = "/analysisItemExcel")
    public String analysisItemExcel(@RequestParam MultipartFile importFile, Order dto) {
        try {
            InputStream inputStream = importFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}