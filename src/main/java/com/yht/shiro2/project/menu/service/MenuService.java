package com.yht.shiro2.project.menu.service;

import java.util.List;
import java.util.Set;

/**
 * 业务处理
 */
public interface MenuService {

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    public Set<String> selectPermsByUserId(Integer userId);
}
