package com.kamall.portal.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
@Api(tags = "测试接口", description = "测试连通性")
public class HelloTestController {
    private static final Logger logger = LoggerFactory.getLogger(HelloTestController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @PreAuthorize("@ka.hasAuthority('sys:super:admin')")
    public String hello() {
        logger.warn("有用户访问了");
        logger.info("有用户访问了");
        logger.debug("有用户访问了");
        logger.error("有用户访问了");
        return "hello world";


    }
}
