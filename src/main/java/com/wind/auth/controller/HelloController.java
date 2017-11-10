package com.wind.auth.controller;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wind.auth.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * HelloController
 *
 * @author qianchun 17/10/9
 **/
@RestController
public class HelloController {
//    @Autowired
//    HelloRemote helloRemote;
//
//    @HystrixCommand(fallbackMethod = "fallback")
//    @RequestMapping("consumer")
//    public String hello(HttpServletRequest request) {
//        System.out.println(request.getLocalAddr().toString() + ":" + request.getLocalPort());
//        return helloRemote.hello();
//    }
//
//
//    public String fallback(HttpServletRequest request) {
//        return "网管路由异常, 启动熔断处理！";
//    }
}
