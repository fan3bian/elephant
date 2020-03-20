package com.fan3bian.elephant.controller;

import com.fan3bian.elephant.domain.Book;
import com.fan3bian.elephant.domain.Foo;
import com.fan3bian.elephant.domain.condition.ReportMasterCondition;
import com.fan3bian.elephant.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class FooController {

    @RequestMapping("/foo")
    public String foo(@Validated Foo foo , BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getDefaultMessage());
                //...
            }
            return "fail";
        }
        return "success";
    }
    /**
     * 添加Book对象
     * @param book
     */
    @RequestMapping(value = "/book", method = {RequestMethod.POST,RequestMethod.GET})
    public void addBook(@RequestBody @Valid Book book) {
        System.out.println(book.toString());
    }
    @RequestMapping(value = "/reading", method = {RequestMethod.POST,RequestMethod.GET})
    public void addReading( @Valid Book book) {
        System.out.println(book.toString());
    }



}
