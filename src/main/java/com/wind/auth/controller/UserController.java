package com.wind.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.wind.annotation.AuthPermission;
import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
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
 * UserController
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference(version = "2.0.0")
    private IUserService userService;

    /**
     * 列表
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.user.list")
    @RequestMapping(value = "/user/list")
    public String list() {
        try {
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, Object> params = new HashMap<>();
            List<User> userList = userService.find(params);
            if (userList != null) {
                resultMap.put("users", userList);
                return JsonResponseUtil.ok(resultMap);
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[用户管理] 用户列表, 异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 新增
     * 
     * @param user 参数
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.user.save")
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String save(User user) {
        try {
            if (user == null || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getRealname())
                    || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getMobile())
                    || (user.getStatus() != Status.ENABLED.getValue()
                            && user.getStatus() != Status.DISABLED.getValue())) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            boolean flag = userService.save(user);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[用户管理] 用户新增, 异常, user={}, e={}", JSONObject.toJSON(user), e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 编辑
     * @param id 菜单ID
     * @return 操作结果
     */
    @AuthPermission(value = "auth.user.edit")
    @RequestMapping(value = "user/edit/{id}")
    public String edit(@PathVariable("id") long id) {
        try {
            User user = userService.findById(id);
            if(user!=null) {
                return JsonResponseUtil.ok(user);
            } else {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
        } catch (Exception e) {
            logger.error("[用户管理] 用户编辑, 异常, id={}, e={}", id, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 更新
     * @param user 表单数据
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.user.update")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update(User user) {
        try {
            if (user == null || StringUtils.isEmpty(user.getRealname())
                    || StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getMobile())
                    || (user.getStatus() != Status.ENABLED.getValue()
                    && user.getStatus() != Status.DISABLED.getValue())) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            user.setUpdateTime(new Date());
            boolean flag = userService.update(user);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[用户管理] 用户更新, 异常, user={}, e={}", JSONObject.toJSON(user), e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 启用/停用
     * @param id 主键ID
     * @param status 状态
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.user.status.change")
    @RequestMapping(value = "/user/status/change/{id}", method = RequestMethod.POST)
    public String statusChange(@PathVariable("id") long id, @RequestParam("status") int status) {
        try {
            if(status!=Status.ENABLED.getValue() && status!=Status.DISABLED.getValue()) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            User user = userService.findById(id);
            if(user==null) {
                return JsonResponseUtil.fail(ErrorCode.NOT_EXIST);
            }
            user.setStatus(status);
            user.setUpdateTime(new Date());
            boolean flag = userService.update(user);
            if (flag) {
                return JsonResponseUtil.ok();
            } else {
                return JsonResponseUtil.fail();
            }
        } catch (Exception e) {
            logger.error("[用户管理] 用户启用/停用, 异常, id={}, status={}, e={}", id, status, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 详情
     *
     * @param id 主键ID
     * @return 返回操作结果
     */
    @AuthPermission(value = "auth.user.detail")
    @RequestMapping(value = "/user/detail/{id}")
    public String detail(@PathVariable("id") long id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            return JsonResponseUtil.ok(user);
        } catch (Exception e) {
            logger.error("[用户管理] 用户详情, 异常, id={}, e={}", id, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }
}
