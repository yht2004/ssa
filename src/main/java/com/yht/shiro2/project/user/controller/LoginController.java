package com.yht.shiro2.project.user.controller;

import com.yht.shiro2.project.menu.service.MenuService;
import com.yht.shiro2.project.role.service.RoleService;
import com.yht.shiro2.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 登陆控制器
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password,boolean rememnerMe){
        
        return null;
    }

}
