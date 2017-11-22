package com.wind.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.wind.annotation.AuthPermission;
import com.wind.auth.model.Role;
import com.wind.auth.service.IRoleService;
import com.wind.common.ErrorCode;
import com.wind.common.Status;
import com.wind.utils.JsonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RoleController
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Reference(version = "2.0.0")
    private IRoleService roleService;

    /**
     * 列表
     * 
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.role.list")
    @RequestMapping(value = "/role/list")
    public String list() {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, Object> params = new HashMap<>();
            List<Role> roleList = roleService.find(params);
            if (roleList != null) {
                resultMap.put("roles", roleList);
                return JsonResponseUtil.ok(resultMap);
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[角色管理] 角色列表, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 新增
     *
     * @param role 参数
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.role.save")
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public String save(Role role) {
        try {
            if (role == null || StringUtils.isEmpty(role.getName()) || (role.getStatus() != Status.ENABLED.getValue()
                    && role.getStatus() != Status.DISABLED.getValue())) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            boolean flag = roleService.add(role);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[角色管理] 角色保存, 异常, role={}, e={}", JSONObject.toJSON(role), e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 编辑
     * 
     * @param id 主键ID
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.role.edit")
    @RequestMapping(value = "role/edit/{id}")
    public String edit(@PathVariable("id") long id) {
        try {
            Role role = roleService.findById(id);
            if (role != null) {
                return JsonResponseUtil.ok(role);
            } else {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("[角色管理] 角色编辑, 异常, id={}, e={}", id, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 更新
     * 
     * @param role 表单数据
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.role.update")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public String update(Role role) {
        try {
            if (role == null || StringUtils.isEmpty(role.getName()) || (role.getStatus() != Status.ENABLED.getValue()
                    && role.getStatus() != Status.DISABLED.getValue())) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            role.setUpdateTime(new Date());
            boolean flag = roleService.update(role);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[角色管理] 角色更新, 异常, role={}, e={}", JSONObject.toJSON(role), e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 启用/停用
     * 
     * @param id 主键ID
     * @param status 状态
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.role.status.change")
    @RequestMapping(value = "/role/status/change/{id}", method = RequestMethod.POST)
    public String statusChange(@PathVariable("id") long id, @RequestParam("status") int status) {
        try {
            if (status != Status.ENABLED.getValue() && status != Status.DISABLED.getValue()) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            Role role = roleService.findById(id);
            if (role == null) {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
            role.setStatus(status);
            role.setUpdateTime(new Date());
            boolean flag = roleService.update(role);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[角色管理] 角色状态更新, 异常, id={}, status={}, e={}", id, status, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    //
    /**
     * 详情
     *
     * @param id 主键ID
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.role.detail")
    @RequestMapping(value = "/role/detail/{id}")
    public String detail(@PathVariable("id") long id) {
        try {
            Role role = roleService.findById(id);
            if (role == null) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            return JsonResponseUtil.ok(role);
        } catch (Exception e) {
            logger.error("[角色管理] 角色详情, 异常, id={}, e={}", id, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }
}
