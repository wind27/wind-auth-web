package com.wind.auth.controller;

import com.wind.annotation.AuthPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    // 登录页
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    // 首页
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
    @AuthPermission(value = "auth.menu.index")
    @RequestMapping("/menu")
    public String menu() {
        return "menu/index";
    }

    @AuthPermission(value = "auth.menu.add")
    @RequestMapping("/menu/add")
    public String add() {
        return "menu/add";
    }

    @AuthPermission(value = "auth.menu.edit")
    @RequestMapping("/menu/edit")
    public String edit() {
        return "menu/edit";
    }

    @AuthPermission(value = "auth.menu.detail")
    @RequestMapping("/menu/detail")
    public String detail() {
        return "menu/detail";
    }
    // 菜单管理页面跳转 end

    // 用户管理页面跳转 start
    @AuthPermission(value = "auth.user.list")
    @RequestMapping("/user")
    public String userList() {
        return "user/index";
    }
    // 用户管理页面跳转 end

    // 应用管理页面跳转 start
    @AuthPermission(value = "auth.app.list")
    @RequestMapping("/app")
    public String appList() {
        return "app/index";
    }
    // 应用管理页面跳转 end

    // 用户组管理页面跳转 start
    @AuthPermission(value = "auth.group.list")
    @RequestMapping("/group")
    public String groupList() {
        return "group/index";
    }
    // 用户组管理页面跳转 end

    // 角色页面跳转 start
    @AuthPermission(value = "auth.role.list")
    @RequestMapping("/role")
    public String roleList() {
        return "role/index";
    }
    // 角色页面跳转 end

    // 权限管理页面跳转 start
    @AuthPermission(value = "auth.permission.list")
    @RequestMapping("/permission")
    public String permissionList() {
        return "permission/index";
    }
    // 权限管理页面跳转 end

    // 临时测试
    @RequestMapping("/imageShow")
    public String imageShow(Model model) {
        return "imageShow";
    }

    // 没权限页
    @RequestMapping("/nopermission")
    public String nopermission(Model model) {
        return "nopermission";
    }

}
