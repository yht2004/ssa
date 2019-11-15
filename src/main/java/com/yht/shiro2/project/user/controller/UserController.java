package com.yht.shiro2.project.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/system/user")
    public String user(){
        return "system/user/user";
    }
}
