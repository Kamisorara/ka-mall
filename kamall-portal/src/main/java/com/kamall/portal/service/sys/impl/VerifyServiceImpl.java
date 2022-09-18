package com.kamall.portal.service.sys.impl;

import com.kamall.common.util.RedisCache;
import com.kamall.portal.service.sys.VerifyService;

import javax.annotation.Resource;

public class VerifyServiceImpl implements VerifyService {

    @Resource
    private RedisCache redisCache;

    @Override
    public boolean verifyRegisterCode(String mailAddr, String verifyCode) {

        String key = "verify:code:" + mailAddr;

        String code = redisCache.getCacheObject(key);
        if (code == null) {
            return false;
        }
        if (!code.equals(verifyCode)) {
            return false;
        }
        redisCache.deleteObject(key);
        return true;
    }
}
