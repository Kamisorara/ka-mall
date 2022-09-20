package com.kamall.portal.service.sys.impl;

import com.kamall.common.exception.Asserts;
import com.kamall.common.util.RedisCache;
import com.kamall.portal.service.sys.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VerifyServiceImpl implements VerifyService {

    @Resource
    private RedisCache redisCache;
    @Autowired
    StringRedisTemplate template;

    @Override
    public boolean verifyRegisterCode(String mailAddr, String verifyCode) {
        String key = "verify:code:" + mailAddr;
        String code = redisCache.getCacheObject(key).toString(); //记得使用toString方法不然会判断为空
        if (code == null) {
            Asserts.fail("验证码服务发生未知错误请重试");
        }
        if (!code.equals(verifyCode)) {
            Asserts.fail("验证码错误");
        }
        redisCache.deleteObject(key);
        return true;
    }
}
