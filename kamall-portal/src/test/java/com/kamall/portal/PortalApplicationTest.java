package com.kamall.portal;

import com.kamall.portal.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest

public class PortalApplicationTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    void test1() {
        String password = new BCryptPasswordEncoder().encode("123456");
//        String password = "123456";
        System.out.println(password);


    }
}
