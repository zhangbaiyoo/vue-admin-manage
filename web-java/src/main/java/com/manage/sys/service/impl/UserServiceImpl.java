package com.manage.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.manage.VO.Result;
import com.manage.sys.entity.User;
import com.manage.sys.mapper.UserMapper;
import com.manage.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangBai
 * @since 2023-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user) {
        //根据用户查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        wrapper.eq(User::getPassword, user.getPassword());
        User loginUser = this.baseMapper.selectOne(wrapper);
        //不为空 生成token 并将用户信息存入redis
        if (loginUser != null) {
            //暂时用UUID -> 最终jwt
            String key = "user:" + UUID.randomUUID();
            //存redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(key, loginUser, 24, TimeUnit.HOURS);
            //返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 根据token获取用户信息 redis
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj != null) {
            User user = JSON.parseObject(JSON.toJSONString(obj), User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name",user.getUsername());
            data.put("avatar", user.getAvatar());
            // 角色
            List<String> rolesList = this.baseMapper.getRoleNameByUserId(user.getId());
            data.put("roles", rolesList);
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }
}
