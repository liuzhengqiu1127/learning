package com.lzq.spring.cloud.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/web")
public class WebController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name")String name)
    {
        log.info("request first name is " + name);
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            log.error("hello error.",e);
        }
        return "hello "+name + ",this is first message.";
    }
}
