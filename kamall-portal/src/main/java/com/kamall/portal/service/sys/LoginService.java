package com.kamall.portal.service.sys;

import com.kamall.common.api.CommonResult;
import com.kamall.common.entity.User;

import java.util.Map;

/**
 * 用户登录注册Service
 */
public interface LoginService {

    //用户登录(返回token)
    String login(String userName, String password);

    //用户注册

}
