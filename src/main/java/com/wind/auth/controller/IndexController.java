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
        model.addAttribute("menus", menuList);

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

    @RequestMapping("/footer")
    public String footer(Model model) {
        return "common/footer";
    }

}
