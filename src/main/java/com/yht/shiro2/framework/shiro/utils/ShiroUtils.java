package com.yht.shiro2.framework.shiro.utils;

import com.yht.shiro2.project.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 */
public class ShiroUtils {

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static User getUser(){
        return (User) getSubject().getPrincipal();
    }

    /**
     * 获取用户ID
     * @return
     */
    public static Integer getUserId(){
        return getUser().getUserId().intValue();
    }
}
