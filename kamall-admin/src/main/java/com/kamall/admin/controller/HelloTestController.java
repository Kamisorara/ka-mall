package com.kamall.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
public class HelloTestController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @PreAuthorize("@ka.hasAuthority('sys:super:admin')")
    public String hello() {
        return "hello world";
    }
}
