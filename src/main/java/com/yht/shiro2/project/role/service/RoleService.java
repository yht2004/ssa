package com.yht.shiro2.project.role.service;

import java.util.Set;

/**
 * 业务处理
 */
public interface RoleService {

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    public Set<String> selectRoleByUserId(Integer userId);
}
