package com.manage.sys.mapper;

import com.manage.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangBai
 * @since 2023-08-03
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> getRoleNameByUserId(Integer id);
}
