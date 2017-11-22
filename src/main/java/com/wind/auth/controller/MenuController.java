package com.wind.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wind.annotation.AuthPermission;
import com.wind.auth.model.Menu;
import com.wind.auth.service.IMenuService;
import com.wind.common.ErrorCode;
import com.wind.utils.JsonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

/**
 * MenuController
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class MenuController {
    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Reference(version = "2.0.0")
    private IMenuService menuService;

    /**
     * 列表
     * 
     * @return
     */
    @AuthPermission(value = "auth.menu.list")
    @RequestMapping(value = "/menu/list")
    public String list() {
        try {
            Map<String, Object> reulstMap = new HashMap<>();
            Map<String, Object> params = new HashMap<>();

            List<Menu> menuList = menuService.find(params);
            if (menuList != null) {
                reulstMap.put("menus", menuList);
                return JsonResponseUtil.ok(reulstMap);
            } else {
                return JsonResponseUtil.fail(ErrorCode.FAIL);
            }
        } catch (Exception e) {
            logger.error("[菜单管理] 菜单列表, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 新增
     * 
     * @param menu 新增参数
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.menu.save")
    @RequestMapping(value = "menu/save", method = RequestMethod.POST)
    public String save(Menu menu) {
        try {
            if (menu == null || StringUtils.isEmpty(menu.getName()) || StringUtils.isEmpty(menu.getUrl())) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            menu.setCreateTime(Instant.now());
            menu.setUpdateTime(Instant.now());
            boolean flag = menuService.add(menu);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[菜单管理] 菜单新增, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 编辑
     *
     * @param id 菜单ID
     * @return 操作结果
     */
    @AuthPermission(value = "auth.menu.edit")
    @RequestMapping(value = "menu/edit/{id}")
    public String edit(@PathVariable("id") long id) {
        try {
            Menu menu = menuService.findById(id);
            if (menu == null) {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
            return JsonResponseUtil.ok(menu);
        } catch (Exception e) {
            logger.error("[菜单管理] 菜单编辑, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 更新
     * 
     * @param menu 更新参数对象
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.menu.update")
    @RequestMapping(value = "menu/update", method = RequestMethod.POST)
    public String update(Menu menu) {
        try {
            if (menu == null) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            Menu currentMuen = menuService.findById(menu.getId());
            if (currentMuen == null) {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
            currentMuen.setUrl(menu.getUrl());
            currentMuen.setName(menu.getName());
            currentMuen.setStatus(menu.getStatus());
            currentMuen.setUpdateTime(Instant.now());
            currentMuen.setAppId(menu.getAppId());
            currentMuen.setParentId(0);

            boolean flag = menuService.update(menu);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[菜单管理] 菜单更新, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 启用/停用
     *
     * @param id 菜单ID
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.menu.status.change")
    @RequestMapping(value = "/menu/status/change/{id}")
    public String statusChange(@PathVariable("id") long id) {
        try {
            Menu menu = menuService.findById(id);
            if (menu == null) {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
            boolean flag = menuService.update(menu);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[菜单管理] 菜单启用/停用, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 菜单详情
     * 
     * @param id 主键ID
     * @return 返回结果
     */
    @AuthPermission(value = "auth.menu.detail")
    @RequestMapping("/menu/detail/{id}")
    public String getById(@PathVariable("id") long id) {
        try {
            Menu menu = menuService.findById(id);
            if (menu == null) {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
            return JsonResponseUtil.ok(menu);
        } catch (Exception e) {
            logger.error("[菜单管理] 菜单详情, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }
}
