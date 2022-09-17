package com.kamall.portal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { //组件搜索
        "com.kamall.security",
        "com.kamall.portal",
        "com.kamall.common"
})
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ka-mall前台系统启动成功   ლ(´ڡ`ლ)ﾞ  \n"
                + ".-.                                .-.  .-.  \n" +
                ": :.-.                             : :  : :  \n" +
                ": `'.' .--.  _____ ,-.,-.,-. .--.  : :  : :  \n" +
                ": . `.' .; ;:_____:: ,. ,. :' .; ; : :_ : :_ \n" +
                ":_;:_;`.__,_;      :_;:_;:_;`.__,_;`.__;`.__;\n" +
                "                                             ");
    }

}
