package com.kamall.portal.controller.sys;

import com.kamall.common.api.CommonResult;
import com.kamall.portal.service.sys.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sso")
@Api(tags = "SysUserController用户接口", description = "用户登录注册相关接口，匿名访问")
public class SysUserController {


    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private LoginService loginService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String userName,
                              @RequestParam String password) {
        String token = loginService.login(userName, password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        logger.info(userName + "登录了");
        return CommonResult.success(tokenMap);
    }

}
