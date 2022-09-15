package com.kamall.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
public class HelloTestController {
    @GetMapping("hello")
    @PreAuthorize("@ka.hasAuthority('sys:super:admin')")
    public String hello() {
        return "hello world";
    }
}
