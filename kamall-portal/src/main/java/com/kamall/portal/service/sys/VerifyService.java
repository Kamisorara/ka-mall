package com.kamall.portal.service.sys;

/**
 * 验证码验证相关service
 */
public interface VerifyService {
    //验证邮件验证码
    boolean verifyRegisterCode(String mailAddr, String verifyCode);
}
