package com.wind.auth.common;

import com.alibaba.fastjson.JSONObject;
import com.wind.annotation.AuthPermission;
import com.wind.annotation.DAO;
import com.wind.auth.controller.IndexController;
import com.wind.common.MultipleDataSources;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.autoconfigure.ShellProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * SpringApplicationContextAware
 *
 * @author qianchun 17/7/24
 **/
@Component
public class SpringApplicationContextAware implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;

            //初始化权限映射缓存
            this.initAuthPermissionUrlMap();
            System.out.println(JSONObject.toJSON(CacheMap.authPermissionUrlMap));
        }
    }

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static void initAuthPermissionUrlMap() {
        Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(Controller.class);
        if (controllerMap == null || controllerMap.size() == 0) {
            return;
        }
        for (String key : controllerMap.keySet()) {
            Object clazz = controllerMap.get(key);// 获取controller对象
            if (clazz == null) {
                continue;
            }

            String parentUrl = "";
            if(clazz.getClass().isAnnotationPresent(Controller.class)) {
                parentUrl = clazz.getClass().getAnnotation(Controller.class).value();
            }

            if(clazz.getClass().getMethods()==null || clazz.getClass().getMethods().length==0) {
                continue;
            }
            for (Method method : clazz.getClass().getMethods()) {
                if (!method.isAnnotationPresent(AuthPermission.class)) {
                    continue;
                }

                String permissionValue = method.getAnnotation(AuthPermission.class).value();
                String[] urls = method.getAnnotation(RequestMapping.class).value();
                if(urls==null || urls.length==0) {
                    continue;
                }
                for(String url : urls) {
                    if(!StringUtils.isEmpty(parentUrl) && !parentUrl.equals("/")) {
                        url = parentUrl + url;
                    }
                    if(!CacheMap.authPermissionUrlMap.containsKey(url)) {
                        CacheMap.authPermissionUrlMap.put(url, permissionValue);
                    }
                }
            }
        }
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);

    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
