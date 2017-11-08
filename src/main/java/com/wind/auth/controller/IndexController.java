package com.wind.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IndexController
 *
 * @author qianchun 17/11/1
 **/
@Controller
public class IndexController {
    @RequestMapping("/")
    public String homepage(Model model, @RequestParam(value="name", required = false) String name) {
        if(StringUtils.isEmpty(name)) {
            name = "wind";
        }
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/user")
    public String user(Model model) {
        return "user";
    }
    @RequestMapping("/role")
    public String role(Model model) {
        return "role";
    }
    @RequestMapping("/permission")
    public String permission(Model model) {
        return "permission";
    }

    @RequestMapping("/imageShow")
    public String imageShow(Model model) {
        return "imageShow";
    }

}
