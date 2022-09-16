package com.kamall.common.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户登录记录(sys_user_login_log)表实体类
 *
 * @author Kamisora
 * @since 2022-09-16 22:49:41
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user_login_log")
public class UserLoginLog {

    private Long id;

    private Long userId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String ip;

    private String city;
    //登录类型：0->PC；1->android;2->ios;3->小程序
    private Integer loginType;

    private String province;

}

