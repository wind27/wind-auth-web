package com.wind.auth.controller;

import com.wind.annotation.AuthPermission;
import org.springframework.http.HttpRequest;
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

    public IndexController() {

//        System.out.println("HHHHHH");
    }

    @RequestMapping("/")
    public String homepage(Model model, @RequestParam(value = "name", required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            name = "wind";
        }
        model.addAttribute("name", name);
        model.addAttribute("v", System.currentTimeMillis());
        return "index";
    }

    // 菜单管理页面跳转 start
    @RequestMapping("/menu")
    public String menu() {
        return "menu/index";
    }

    @RequestMapping("/menu/add")
    public String add() {
        return "menu/add";
    }

    @RequestMapping("/menu/edit")
    public String edit() {
        return "menu/edit";
    }

    @RequestMapping("/menu/detail")
    public String detail() {
        return "menu/detail";
    }
    // 菜单管理页面跳转 end

    // 应用管理页面跳转 start
    @RequestMapping("/app")
    public String app(Model model) {
        return "app/index";
    }
    // 应用管理页面跳转 end

    // 用户组管理页面跳转 start
    @RequestMapping("/group")
    public String group(Model model) {
        return "group/index";
    }
    // 用户组管理页面跳转 end

    // 角色页面跳转 start
    @RequestMapping("/role")
    public String role(Model model) {
        return "role/index";
    }
    // 角色页面跳转 end

    // 权限管理页面跳转 start
    @RequestMapping("/permission")
    public String permission(Model model) {
        return "permission/index";
    }
    // 权限管理页面跳转 end

    @RequestMapping("/imageShow")
    public String imageShow(Model model) {
        return "imageShow";
    }


    @AuthPermission(value = "auth.nopermission")
    @RequestMapping("/nopermission")
    public String nopermission(Model model) {
        return "nopermission";
    }

}
