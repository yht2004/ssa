package com.yht.shiro2.framework.web.base;

import com.yht.shiro2.framework.shiro.utils.ShiroUtils;
import com.yht.shiro2.project.user.entity.User;

public class BaseController {

    public User getUser(){
        return ShiroUtils.getUser();
    }
}
