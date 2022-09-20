package com.kamall.portal.service.sys.impl;


import com.kamall.common.constant.RabbitMqConstant;
import com.kamall.common.entity.User;
import com.kamall.common.entity.UserLoginLog;
import com.kamall.common.exception.Asserts;
import com.kamall.common.service.ipService;
import com.kamall.common.util.JwtUtil;
import com.kamall.common.util.RedisCache;
import com.kamall.portal.dao.UserLoginLogMapper;
import com.kamall.portal.dao.UserMapper;
import com.kamall.portal.service.UserService;
import com.kamall.portal.service.sys.LoginService;
import com.kamall.portal.service.sys.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserService userService;

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerifyService verifyService;

    @Resource
    private ipService ipService;

    @Resource
    private RabbitTemplate rabbitTemplate;


    @Override
    public String login(String userName, String password) {
        String token = null;
        try {
            UserDetails userDetails = userService.loadUserByUsername(userName);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            //获取获取用户id
            User user = userMapper.getUserByName(userName);
            //账号是否被暂停使用
            if (user.getStatus().equals("0")) {
                String userId = user.getId().toString();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //生成jwt
                token = JwtUtil.createJWT(userId);
                //把完整的用户信息存入redis
                insertLoginLog(Long.parseLong(userId));
                redisCache.setCacheObject("login:" + userId, userDetails);
            } else {
                Asserts.fail("该账号已被停用");
            }
        } catch (AuthenticationException e) {
            logger.error("{ 登录异常 }" + e.getMessage());
        }
        return token;
    }

    @Override
    public void insertLoginLog(Long userId) {
        UserLoginLog userLoginLog = new UserLoginLog();
        userLoginLog.setUserId(userId);
        //查询ip地址
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String ipAddr = ipService.getUserInquireIpAddress(attributes);
        userLoginLog.setIp(ipAddr);
        userLoginLogMapper.insert(userLoginLog);
    }

    @Override
    public String register(String userName,
                           String password,
                           String passwordRepeat,
                           String emailAddr,
                           String emailVerifyCode) {
        //后端重新认证账号重复密码
        if (verifyService.verifyRegisterCode(emailAddr, emailVerifyCode) && password.equals(passwordRepeat)) {
            try {
                User user = new User();
                user.setUserName(userName);
                user.setPassword(passwordEncoder.encode(password));
                user.setEmail(emailAddr);
                userMapper.insert(user);
                //使用rabbitMQ发送注册成功邮件信息
                rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE, RabbitMqConstant.EMAIL_ROUTING_KEY, emailAddr);
            } catch (Exception e) {
                Asserts.fail("{ 注册异常 }" + e.getMessage());
            }
        } else {
            Asserts.fail("{ 注册异常 }" + "操作ip地址:" + ipService.getUserInquireIpAddress((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()));
        }
        return "注册成功";
    }
}
