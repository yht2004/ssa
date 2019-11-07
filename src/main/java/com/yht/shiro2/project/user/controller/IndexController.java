package com.yht.shiro2.project.user.controller;

import com.yht.shiro2.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController extends BaseController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }
}
