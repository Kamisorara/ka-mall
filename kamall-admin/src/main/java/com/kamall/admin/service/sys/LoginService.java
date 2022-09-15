package com.kamall.admin.service.sys;

/**
 * 用户登录Service
 */
public interface LoginService {

    //用户登录(返回token)
    String login(String userName, String password);

}
