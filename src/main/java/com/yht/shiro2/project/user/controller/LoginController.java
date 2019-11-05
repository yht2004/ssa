package com.yht.shiro2.project.user.controller;

import com.yht.shiro2.framework.shiro.utils.ShiroUtils;
import com.yht.shiro2.project.menu.entity.Menu;
import com.yht.shiro2.project.menu.service.MenuService;
import com.yht.shiro2.project.role.service.RoleService;
import com.yht.shiro2.project.user.entity.User;
import com.yht.shiro2.project.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


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
    public String login(String username, String password, boolean rememberMe, Model model){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码不正确");
        }catch (UnknownAccountException e){
            model.addAttribute("msg","账号不存在");
        }catch (AuthenticationException e){
            model.addAttribute("msg","状态不正常");
        }
        if (subject.isAuthenticated()){
            System.out.println("认证成功");
            User user = userService.selectUserByLoginName(username);
            List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
            model.addAttribute("currentUser",username);
            model.addAttribute("menus",menus);
            return "index";
        }else {
            token.clear();
            return "login";
        }
    }


}
