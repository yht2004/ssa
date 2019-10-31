package com.yht.shiro2.project.user.service;

import com.yht.shiro2.project.user.entity.User;

/**
 * 业务处理
 */
public interface UserService {

    /**
     * 根据登陆名查找用户
     * @param loginName
     * @return
     */
    public User selectUserByLoginName(String loginName);
}
