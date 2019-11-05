package com.yht.shiro2.project.menu.service;

import com.yht.shiro2.project.menu.entity.Menu;

import java.util.List;
import java.util.Set;

/**
 * 业务处理
 */
public interface MenuService {


    /**
     * 查询全部菜单
     * @return
     */
    public List<Menu> allMenus();

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    public Set<String> selectPermsByUserId(Integer userId);

    /**
     * 根据用户ID查询菜单
     * @param userId
     * @return
     */
    public List<Menu> selectMenusByUserId(Integer userId);
}
