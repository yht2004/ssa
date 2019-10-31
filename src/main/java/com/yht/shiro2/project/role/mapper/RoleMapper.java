package com.yht.shiro2.project.role.mapper;

import com.yht.shiro2.project.role.entity.Role;

import java.util.List;

/**
 * 数据层 role
 */
public interface RoleMapper {

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> selectRolesAll();

    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    public List<Role> selectRolesByUserId(Integer userId);

}
