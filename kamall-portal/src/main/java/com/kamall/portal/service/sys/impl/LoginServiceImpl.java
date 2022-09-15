package com.kamall.portal.service.sys.impl;


import com.kamall.common.entity.User;
import com.kamall.common.util.JwtUtil;
import com.kamall.common.util.RedisCache;
import com.kamall.portal.dao.UserMapper;
import com.kamall.portal.service.UserService;
import com.kamall.portal.service.sys.LoginService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(String userName, String password) {
        String token = null;
        try {
            UserDetails userDetails = userService.loadUserByUsername(userName);
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            //获取获取用户id
            User user = userMapper.getUserByName(userName);
            String userId = user.getId().toString();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成jwt
            token = JwtUtil.createJWT(userId);
            //把完整的用户信息存入redis
            redisCache.setCacheObject("login:" + userId, userDetails);
        } catch (AuthenticationException e) {
            //登录失败 记入日志中 （还未配置日志信息）
            System.out.println("{登录异常}" + e.getMessage());
        }
        return token;
    }
}