package com.kamall.portal.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kamall.common.entity.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录日志mapper
 */
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLog> {

}
