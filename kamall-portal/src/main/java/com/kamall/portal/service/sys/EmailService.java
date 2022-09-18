package com.kamall.portal.service.sys;

/**
 * 邮件发送Service
 */
public interface EmailService {

    //发送注册成功邮件信息
    void sendRegisterSuccessMail(String mailAddr);

    //发送邮件验证码
    void sendVerifyCode(String mail);


}
