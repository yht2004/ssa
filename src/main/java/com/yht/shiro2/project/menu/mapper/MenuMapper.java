package com.yht.shiro2.project.menu.mapper;

import com.yht.shiro2.project.menu.entity.Menu;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据用户ID查询菜单
     * @param userId
     * @return
     */
    public List<Menu> selectMenuByUserId(Integer userId);

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    public List<String> selectPermsByUserId(Integer userId);
}
