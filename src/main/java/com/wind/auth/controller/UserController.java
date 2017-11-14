package com.wind.auth.controller;

import com.wind.auth.model.User;
import com.wind.auth.service.IUserService;
import com.wind.common.ErrorCode;
import com.wind.utils.JsonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * UserController
 *
 * @author qianchun 17/11/1
 **/
@RestController
public class UserController {
    //    @Reference
    @Autowired
    private IUserService userService;

    @RequestMapping("/user/{id}")
    public String imageShow( @PathVariable("id") long id) {
        if(id<=0) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        User user = userService.findById(id);
        if(user==null) {
            return JsonResponseUtil.fail(ErrorCode.PARAM_ERROR);
        }
        return JsonResponseUtil.ok(user);
    }

    @RequestMapping("save")
    public String saveUser(){
        User user = new User();
        user.setCreateTime(new Date());
        user.setRealname("realname");
        user.setUsername("username");
        if (userService.save(user)==null){
            return JsonResponseUtil.fail(ErrorCode.ERROR);
        }
        return JsonResponseUtil.ok();
    }
}
