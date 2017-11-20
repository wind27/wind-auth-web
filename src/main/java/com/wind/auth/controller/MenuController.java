package com.wind.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wind.auth.model.Menu;
import com.wind.auth.model.User;
import com.wind.auth.service.IMenuService;
import com.wind.auth.service.IUserService;
import com.wind.common.ErrorCode;
import com.wind.utils.JsonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * UserController
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class MenuController {
    private static Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Reference(version = "2.0.0")
    private IMenuService menuService;

    @RequestMapping(value = "/menu/list")
    public String list() {
        Map<String, Object> reulstMap = new HashMap<String, Object>();
        Map<String, Object> params = new HashMap<>();

        List<Menu> menuList = menuService.find(params);
        if(menuList!=null) {
            reulstMap.put("menus", menuList);
            return JsonResponseUtil.ok(reulstMap);
        } else {
            return JsonResponseUtil.fail(ErrorCode.FAIL);
        }
    }

    /**
     * 启用
     * @param id 菜单ID
     * @return 返回操作结果
     */
    @RequestMapping(value = "/menu/enable/{id}")
    public String enable(@PathVariable("id") long id) {
        if(id<=0) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        boolean flag = menuService.enable(id);
        if(flag) {
            return JsonResponseUtil.ok();
        } else {
            return JsonResponseUtil.fail();
        }
    }

    /**
     * 禁用
     * @param id 菜单ID
     * @return 返回操作结果
     */
    @RequestMapping(value = "menu/disable/{id}")
    public String disable(@PathVariable("id") long id) {
        if(id<=0) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        boolean flag = menuService.disable(id);
        if(flag) {
            return JsonResponseUtil.ok();
        } else {
            return JsonResponseUtil.fail();
        }
    }

    /**
     * 编辑
     * @param id 菜单ID
     * @return 操作结果
     */
    @RequestMapping(value = "menu/edit/{id}")
    public String edit(@PathVariable("id") long id) {
        try {
            Menu menu = menuService.findById(id);
            if(menu!=null) {
                return JsonResponseUtil.ok(menu);
            } else {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("[菜单管理] 编辑菜单, 异常 e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }


    /**
     * 新增
     * @param menu 新增参数
     * @return 返回操作结果
     */
    @RequestMapping(value="menu/save", method = RequestMethod.POST)
    public String save(Menu menu) {
        if(menu==null || StringUtils.isEmpty(menu.getName()) || StringUtils.isEmpty(menu.getUrl())) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        boolean flag = menuService.add(menu);
        if(flag) {
            return JsonResponseUtil.ok();
        } else {
            return JsonResponseUtil.fail();
        }
    }
    /**
     * 更新
     * @param menu 更新参数对象
     * @return 返回操作结果
     */
    @RequestMapping(value="menu/update", method = RequestMethod.POST)
    public String update(Menu menu) {
        if(menu==null) {
            return  JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }

        Menu currentMuen = menuService.findById(menu.getId());
        if(currentMuen==null) {
            return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
        }
        currentMuen.setUrl(menu.getUrl());
        currentMuen.setName(menu.getName());
        currentMuen.setStatus(menu.getStatus());
        currentMuen.setUpdateTime(new Date());
        currentMuen.setAppId(menu.getAppId());
        currentMuen.setParentId(0);

        boolean flag = menuService.update(menu);
        if(flag) {
            return JsonResponseUtil.ok();
        } else {
            return JsonResponseUtil.fail();
        }
    }

    /**
     * 菜单详情
     * @param id 主键ID
     * @return 返回结果
     */
    @RequestMapping("/menu/detail/{id}")
    public String getById(@PathVariable("id") long id) {
        if(id<=0) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        Menu menu = menuService.findById(id);
        if(menu!=null) {
            return JsonResponseUtil.ok(menu);
        } else {
            return JsonResponseUtil.fail(ErrorCode.FAIL);
        }
    }
}
