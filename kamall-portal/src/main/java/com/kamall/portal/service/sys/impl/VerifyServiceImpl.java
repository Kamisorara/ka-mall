package com.kamall.portal.service.sys.impl;

import com.kamall.common.exception.Asserts;
import com.kamall.common.util.RedisCache;
import com.kamall.portal.service.sys.VerifyService;
import io.jsonwebtoken.lang.Assert;

import javax.annotation.Resource;

public class VerifyServiceImpl implements VerifyService {

    @Resource
    private RedisCache redisCache;

    @Override
    public boolean verifyRegisterCode(String mailAddr, String verifyCode) {

        String key = "verify:code:" + mailAddr;

        String code = redisCache.getCacheObject(key);
        if (code == null) {
            Asserts.fail("验证码服务发生维持错误请重试");
        }
        if (!code.equals(verifyCode)) {
            Asserts.fail("验证码错误");
        }
        redisCache.deleteObject(key);
        return true;
    }
}
