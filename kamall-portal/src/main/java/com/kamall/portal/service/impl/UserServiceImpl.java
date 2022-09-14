package com.kamall.portal.service.impl;

import com.kamall.common.entity.User;
import com.kamall.portal.dao.UserMapper;
import com.kamall.portal.service.UserService;
import com.kamall.security.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByName(String userName) {
        User user = userMapper.getUserByName(userName);
        if (user == null) {
            return user;
        }
        //用户权限还未进开发
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = getUserByName(userName);
        if (user != null) {
            return new LoginUser(user, null); //权限还未实现添加
        }
        //否则返回错误
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
