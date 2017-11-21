package com.wind.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wind.annotation.AuthPermission;
import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
import com.wind.common.ErrorCode;
import com.wind.utils.JsonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * IndexController
 *
 * @author qianchun 17/11/1
 **/
@Controller
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Reference(version = "2.0.0")
    private IUserService userService;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            User user = userService.findUsername(username);
            if(user!=null && password.equals(user.getPassword())){
                return JsonResponseUtil.ok();
            }
            return JsonResponseUtil.fail();
        } catch (Exception e) {
            logger.error("[登录] 登录异常, username={}, password={}, e={}", username, password, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
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

    // 应用管理页面跳转 start
    @AuthPermission(value = "auth.app.list")
    @RequestMapping("/app")
    public String app(Model model) {
        return "app/index";
    }
    // 应用管理页面跳转 end

    // 用户组管理页面跳转 start
    @AuthPermission(value = "auth.group.list")
    @RequestMapping("/group")
    public String group(Model model) {
        return "group/index";
    }
    // 用户组管理页面跳转 end

    // 角色页面跳转 start
    @AuthPermission(value = "auth.role.list")
    @RequestMapping("/role")
    public String role(Model model) {
        return "role/index";
    }
    // 角色页面跳转 end

    // 权限管理页面跳转 start
    @AuthPermission(value = "auth.permission.list")
    @RequestMapping("/permission")
    public String permission(Model model) {
        return "permission/index";
    }
    // 权限管理页面跳转 end

    @RequestMapping("/imageShow")
    public String imageShow(Model model) {
        return "imageShow";
    }

    @RequestMapping("/nopermission")
    public String nopermission(Model model) {
        return "nopermission";
    }

}
