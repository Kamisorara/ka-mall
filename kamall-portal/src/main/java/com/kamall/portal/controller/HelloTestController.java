package com.kamall.portal.controller;

import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
@Api(tags = "测试接口", description = "测试连通性")
public class HelloTestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @PreAuthorize("@ka.hasAuthority('sys:super:admin')")
    public String hello() {
        return "hello world";
    }
}
