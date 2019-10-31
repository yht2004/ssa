package com.yht.shiro2.project.user.service;

import com.yht.shiro2.project.user.entity.User;
import com.yht.shiro2.project.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名查询用户
     * @param loginName
     * @return
     */
    @Override
    public User selectUserByLoginName(String loginName) {
        return userMapper.selectUserByLoginName(loginName);
    }
}
