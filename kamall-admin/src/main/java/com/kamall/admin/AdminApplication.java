package com.kamall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { //组件搜索
        "com.kamall.security",
        "com.kamall.admin",
        "com.kamall.common"
})
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ka-mall后台管理系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .--.    .-.          _       \n" +
                ": .; :   : :         :_;      \n" +
                ":    : .-' :,-.,-.,-..-.,-.,-.\n" +
                ": :: :' .; :: ,. ,. :: :: ,. :\n" +
                ":_;:_;`.__.':_;:_;:_;:_;:_;:_;");
    }
}
