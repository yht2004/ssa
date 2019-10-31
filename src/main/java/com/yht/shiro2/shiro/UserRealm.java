package com.yht.shiro2.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取用户名
        String username = (String) principal.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色
        info.addRole("");
        //添加权限
        info.addStringPermission("");

        return info;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        if (username == null){
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,"123456",getName());
        return info;
    }
}
