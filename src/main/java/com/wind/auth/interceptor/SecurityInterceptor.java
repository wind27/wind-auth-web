package com.wind.auth.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SecurityInterceptor
 *
 * @author qianchun 17/11/20
 **/
public class SecurityInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        httpServletRequest.setAttribute("startTime",System.currentTimeMillis());

        //权限校验
        if(httpServletRequest.getRequestURI().equals("/imageShow") || httpServletRequest.getRequestURI().equals("/test")) {
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
