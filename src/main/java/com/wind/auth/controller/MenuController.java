package com.wind.auth.controller;

import com.wind.auth.model.Menu;
import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
import com.wind.common.ErrorCode;
import com.wind.utils.JsonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController
 *
 * @author qianchun 17/11/1
 **/
@RestController("menu")
public class MenuController {
    @RequestMapping("/menu/list")
    public String list() {
        Map<String, Object> reulstMap = new HashMap<String, Object>();
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
        reulstMap.put("menus", menuList);
        if(menuList==null) {
            return JsonResponseUtil.fail(ErrorCode.FAIL);
        }
        return JsonResponseUtil.ok(reulstMap);
    }


    @RequestMapping("/{id}")
    public String getById(@PathVariable("id") long id) {
        if(id<=0) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        Menu menu = new Menu();
//        User user = userService.findById(id);
        if(menu==null) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        return JsonResponseUtil.ok(menu);
    }
}
