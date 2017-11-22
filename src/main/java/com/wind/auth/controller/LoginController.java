package com.wind.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wind.auth.common.Constants;
import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
import com.wind.common.ErrorCode;
import com.wind.common.Status;
import com.wind.utils.CookieUtil;
import com.wind.utils.JsonResponseUtil;
import com.wind.utils.MD5Util;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CommonController 公用数据信息管理
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Reference(version = "2.0.0")
    private IUserService userService;

    /**
     * 登录
     * 
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestParam("username") String username,
            @RequestParam("password") String password) {
        try {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            User user = userService.findByUsername(username);
            if (user == null || !user.getPassword().equals(MD5Util.MD5(password))) {
                return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
            }
            if(user.getStatus()== Status.DISABLED.getValue()) {
                return JsonResponseUtil.fail(ErrorCode.DISABLED);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(MD5Util.MD5(username));
            sb.append("_");
            sb.append(MD5Util.MD5(Constants.AUTH_COOKIE_ENCRYPTION_KEY));
            sb.append("_");
            sb.append(user.getId());
            sb.append("_");
            sb.append(System.currentTimeMillis());
            System.out.println("-------------------"+sb.toString()+"-------------------");
            String cookieValue = new String(Base64.encodeBase64((sb.toString()).getBytes()));
            CookieUtil.addCookie(response, Constants.AUTH_COOKIE_NAME, cookieValue);
            return JsonResponseUtil.ok();
        } catch (Exception e) {
            logger.error("[登录] 登录异常, username={}, password={}, e={}", username, password, e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }

    /**
     * 退出登录
     * 
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtil.removeCookie(request, response, Constants.AUTH_COOKIE_NAME);
            return JsonResponseUtil.ok();
        } catch (Exception e) {
             logger.error("[登录] 退出登录异常, e={}", e);
            return JsonResponseUtil.fail(ErrorCode.SYS_ERROR);
        }
    }
}
