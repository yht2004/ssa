package com.yht.shiro2.framework.shiro.realm;


import com.yht.shiro2.framework.shiro.utils.ShiroUtils;
import com.yht.shiro2.project.menu.service.MenuService;
import com.yht.shiro2.project.role.service.RoleService;
import com.yht.shiro2.project.user.entity.User;
import com.yht.shiro2.project.user.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;


    /**
     * 授权
     * @param principal
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取用户名
        Integer userId = ShiroUtils.getUserId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加角色
        info.setRoles(roleService.selectRoleByUserId(userId));
        //添加权限
        info.setStringPermissions(menuService.selectPermsByUserId(userId));

        return info;
    }

    /**
     * 登陆认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        User user = userService.selectUserByLoginName(username);
        if (user == null){
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,user.getPassword(),getName());
        if (user.getStatus()==1){
            throw new AuthenticationException("该账号已被禁用");
        }
        return info;
    }
}
