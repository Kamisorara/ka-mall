package com.kamall.portal.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kamall.common.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户权限Mapper
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    //根据用户id查询用户所对应的权限
    List<String> selectPermsByUserId(Long id);

}
