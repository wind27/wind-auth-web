package com.wind.auth.controller;

import com.wind.auth.model.Menu;
import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
import com.wind.common.ErrorCode;
import com.wind.utils.JsonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommonController 公用数据信息管理
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class CommonController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/navigation")
    public String navigation() {
        String name = "wind";

        Map<String, Object> mapResult = new HashMap<>();
        List<Menu> menuList = new ArrayList<>();
        Menu menu = new Menu();
        menu.setName("用户管理");
        menu.setUrl("/user/index");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("菜单管理");
        menu.setUrl("/menu/index");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("用户组管理");
        menu.setUrl("/group/index");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("角色管理");
        menu.setUrl("/role/index");
        menuList.add(menu);

        menu = new Menu();
        menu.setName("权限管理");
        menu.setUrl("/permission/index");
        menuList.add(menu);

        mapResult.put("menus", menuList);
        mapResult.put("name", name);
        return JsonResponseUtil.ok(mapResult);
    }
}
