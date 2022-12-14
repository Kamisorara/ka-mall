package com.kamall.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kamall.common.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户User Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //根据用户名搜索用户id
    User getUserByName(String userName);

    //
}
