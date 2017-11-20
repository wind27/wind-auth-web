package com.wind.auth.common;

import com.wind.annotation.DAO;
import com.wind.common.MultipleDataSources;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * SpringApplicationContextAware
 *
 * @author qianchun 17/7/24
 **/
public class CacheMap  {
    public static Map<String, String> authPermissionUrlMap = new HashMap<>();
}
