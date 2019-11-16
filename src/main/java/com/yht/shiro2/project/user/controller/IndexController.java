package com.yht.shiro2.project.user.controller;

import com.yht.shiro2.framework.web.base.BaseController;
import com.yht.shiro2.project.menu.entity.Menu;
import com.yht.shiro2.project.menu.service.MenuService;
import com.yht.shiro2.project.role.service.RoleService;
import com.yht.shiro2.project.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class IndexController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/test")
    public String test(Model model){
        List<Menu> menus = menuService.selectMenusByUserId(1);
        model.addAttribute("menus",menus);
        return "test";
    }
}
