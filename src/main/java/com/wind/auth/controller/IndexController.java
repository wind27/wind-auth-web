package com.wind.auth.controller;

import com.wind.auth.model.App;
import com.wind.auth.model.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Menu> menuList = new ArrayList<>();
        Menu menu = new Menu();
        menu.setName("用户管理");
        menu.setUrl("/user");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("菜单管理");
        menu.setUrl("/menu");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("用户组管理");
        menu.setUrl("/group");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("角色管理");
        menu.setUrl("/role");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("权限管理");
        menu.setUrl("/permission");
        menuList.add(menu);

        model.addAttribute("name", name);
        model.addAttribute("v", System.currentTimeMillis());
        model.addAttribute("menus", menuList);

        return "index";
    }

    //菜单管理页面跳转 start
    @RequestMapping("/menu")
    public String menu(Model model) {
        return "menu/index";
    }
    //菜单管理页面跳转 end

    //应用管理页面跳转 start
    @RequestMapping("/app")
    public String app(Model model) {
        return "app/index";
    }
    //应用管理页面跳转 end

    //用户组管理页面跳转 start
    @RequestMapping("/group")
    public String group(Model model) {
        return "group/index";
    }
    //用户组管理页面跳转 end

    //角色页面跳转 start
    @RequestMapping("/role")
    public String role(Model model) {
        return "role/index";
    }
    //角色页面跳转 end

    //权限管理页面跳转 start
    @RequestMapping("/permission")
    public String permission(Model model) {
        return "permission/index";
    }
    //权限管理页面跳转 end


    @RequestMapping("/imageShow")
    public String imageShow(Model model) {
        return "imageShow";
    }

}
