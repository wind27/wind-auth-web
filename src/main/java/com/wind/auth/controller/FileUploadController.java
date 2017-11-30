package com.wind.auth.controller;

import com.alibaba.dubbo.rpc.Invocation;
import com.wind.common.ImageType;
import com.wind.utils.JsonResponseUtil;

/**
 * FileUploadController
 *
 * @author qianchun 17/11/30
 **/
public class FileUploadController {
    String avaterImageURI = "http://127.0.0.1:9090/monster/";
    String blogImageUrl = "";
    String albumsUrl = "";


    public String uploadAvater(long appId, long id) {
        StringBuilder avaterImageUrl = new StringBuilder();
        avaterImageUrl.append(avaterImageURI);
        avaterImageUrl.append(id);
        avaterImageUrl.append("?size=80");

        return JsonResponseUtil.ok();
    }
}
