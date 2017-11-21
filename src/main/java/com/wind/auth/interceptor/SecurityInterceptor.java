package com.wind.auth.interceptor;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.wind.auth.common.Constants;
import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
import com.wind.common.Status;
import com.wind.utils.CookieUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SecurityInterceptor
 *
 * @author qianchun 17/11/20
 **/
public class SecurityInterceptor implements HandlerInterceptor {
    @Reference(version = "2.0.0")
    private IUserService userService;


    private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());
        httpServletRequest.setAttribute("v", System.currentTimeMillis());
        //校验是否登录
        boolean isLogin = false;
        String cookieValue = CookieUtil.getCookie(httpServletRequest, Constants.AUTH_COOKIE_NAME);
        if(StringUtils.isNotEmpty(cookieValue)) {
            String decode = new String(Base64.decodeBase64(cookieValue.getBytes()));
            String[] decodeSlices = decode.split("_");
            if(decodeSlices.length==4 && StringUtils.isNumeric(decodeSlices[2])) {
                Long id = Long.parseLong(decode.split("_")[2]);
                User user = userService.findById(id);
                if(user!=null&& user.getStatus()== Status.ENABLED.getValue()) {
                    isLogin = true;
                }
            }
        }

        //判断是否未登录状态,访问非登录url, 重定向至登录页面
        if(!httpServletRequest.getRequestURI().equals("/login") && !isLogin) {
            logger.info("[权限拦截器] 请求URI={}, method={}, 未登录", httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
            httpServletResponse.sendRedirect("/login");
        }
        //权限校验
        boolean hasPermission = true;
        if(!httpServletRequest.getRequestURI().equals("/nopermission") && !hasPermission) {
            logger.info("[权限拦截器] 请求URI={}, method={}, 没有权限", httpServletRequest.getRequestURI(), httpServletRequest.getMethod());
            httpServletResponse.sendRedirect("/nopermission");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {
        String uri =  httpServletRequest.getRequestURI().toString();
        String method = httpServletRequest.getMethod();
        Object startTimeObj = httpServletRequest.getAttribute("startTime");
        long startTime = 0L;
        long endTime = System.currentTimeMillis();
        if(startTimeObj!=null && startTimeObj instanceof Long) {
             startTime = (long) httpServletRequest.getAttribute("startTime");
        }
        long costTime = endTime-startTime;

        //打印请求执行信息
        if(!"/nopermission".equals(uri)) {
            logger.info("[权限拦截器] uri={}, method={}, costTime:{}毫秒", uri, method, costTime);
        }
    }
}
