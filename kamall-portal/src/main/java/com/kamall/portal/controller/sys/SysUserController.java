package com.kamall.portal.controller.sys;

import com.kamall.common.api.CommonResult;
import com.kamall.portal.service.sys.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sso")
@Api(tags = "SysUserController用户接口", description = "用户登录注册相关接口，匿名访问")
public class SysUserController {

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
        return CommonResult.success(tokenMap);
    }

}
