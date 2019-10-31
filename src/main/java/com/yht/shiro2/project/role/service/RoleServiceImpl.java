package com.yht.shiro2.project.role.service;

import com.yht.shiro2.project.role.entity.Role;
import com.yht.shiro2.project.role.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 业务处理
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户ID查询用户所拥有的角色
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectRoleByUserId(Integer userId) {
        List<Role> roles = roleMapper.selectRolesByUserId(userId);
        Set<String> roleSet = new HashSet<>();
        for (Role role : roles){
            if (role != null){
                roleSet.addAll(Arrays.asList(role.getRoleKey().trim().split(",")));
            }
        }
        return roleSet;
    }
}
