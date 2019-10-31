package com.yht.shiro2.project.user.controller;

import com.yht.shiro2.project.menu.service.MenuService;
import com.yht.shiro2.project.role.service.RoleService;
import com.yht.shiro2.project.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

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
    public String login(String username, String password, boolean rememnerMe, Map<String,Object> map){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememnerMe);
        Subject subject = SecurityUtils.getSubject();

        try{
            //执行登陆操作
            subject.login(token);
            //登陆成功 跳转
            return "index";
        }catch (AuthenticationException e){
            String msg = "用户名或密码错误";

        }
        return "index";
    }

}
