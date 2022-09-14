package com.kamall.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
public class HelloTestController {
    @GetMapping("hello")
    public String hello() {
        return "hello world";
    }
}
