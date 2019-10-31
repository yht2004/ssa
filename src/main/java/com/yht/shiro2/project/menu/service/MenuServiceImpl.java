package com.yht.shiro2.project.menu.service;

import com.yht.shiro2.project.menu.mapper.MenuMapper;
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
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;
    /**
     * 根据用户ID查询用户所拥有的权限
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectPermsByUserId(Integer userId) {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permSets = new HashSet<>();
        for (String perm : perms){
            if (perm != null){
                permSets.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permSets;
    }
}
