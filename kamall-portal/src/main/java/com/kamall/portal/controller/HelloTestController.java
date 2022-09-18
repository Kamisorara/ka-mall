package com.kamall.portal.controller;

import com.kamall.common.util.RequestUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        //测试登录获取ip地址
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return RequestUtil.getRequestIp(request);
    }
}
