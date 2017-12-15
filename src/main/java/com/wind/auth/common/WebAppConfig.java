package com.wind.auth.common;

import com.wind.auth.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebAppConfig
 *
 * @author qianchun 17/11/20
 **/
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {
    public WebAppConfig() {
        System.out.println("烂机器管理-----------------------------");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.createSecurityInterceptor());
        super.addInterceptors(registry);
    }

//    @Bean
//    public SecurityInterceptor createSecurityInterceptor() {
//        return new SecurityInterceptor();
//    }
}
