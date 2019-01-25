package com.fan3bian.elephant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MessageController
 *
 * @author weitao
 * @date 2018/7/5
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/json")
    public String json(@RequestParam("app_key") String appKey, @RequestParam("method") String method, @RequestParam
            ("pin") String pin, @RequestParam("timestamp") String timestamp, @RequestParam("v") String v,
                       @RequestParam("sign") String sign, @RequestBody String msg) {
        logger.info("appKey:{}", appKey);
        logger.info("method:{}", method);
        logger.info("pin:{}", pin);
        logger.info("timestamp:{}", timestamp);
        logger.info("v:{}", v);
        logger.info("sign:{}", sign);
        logger.info("msg:{}", msg);
        return "gaga:" + msg;
    }
}
