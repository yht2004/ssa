package com.yht.shiro2;

import com.yht.shiro2.project.menu.mapper.MenuMapper;
import com.yht.shiro2.project.menu.service.MenuService;
import com.yht.shiro2.project.role.entity.Role;
import com.yht.shiro2.project.role.mapper.RoleMapper;
import com.yht.shiro2.project.role.service.RoleService;
import com.yht.shiro2.project.user.entity.User;
import com.yht.shiro2.project.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest
class Shiro2ApplicationTests {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        List<String> perms = menuMapper.selectPermsByUserId(1);
        for (String perm : perms){
            System.out.println(perm);
        }
    }


    @Test
    void selectRoleByUserId() {
        List<Role> roles = roleMapper.selectRolesByUserId(1);
        for (Role role : roles){
            System.out.println(role.getRoleName());
        }
    }



    @Test
    void selectRolesByUserId() {
        Set<String> roleSet = roleService.selectRoleByUserId(1);
        Iterator<String> it = roleSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    void selectPermsByUserId() {
        Set<String> set = menuService.selectPermsByUserId(1);
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }


    @Test
    void selectUserByLoginName() {
        User user = userService.selectUserByLoginName("admin");
        System.out.println(user.getLoginName());
    }

    String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
    @Test
    void phonenumberPattern() {
        String phonenumber = "13141196133";
        if (!phonenumber.matches(MOBILE_PHONE_NUMBER_PATTERN)){
            System.out.println("请输入正确的手机号");
        }
    }
}
