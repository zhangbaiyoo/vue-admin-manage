package com.manage.sys.service.impl;

import com.manage.sys.entity.User;
import com.manage.sys.mapper.UserMapper;
import com.manage.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangBai
 * @since 2023-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
