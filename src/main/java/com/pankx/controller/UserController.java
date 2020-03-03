package com.pankx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片上传的控制器类
 */
@Controller
@RequestMapping("/upload")
public class UserController {

    /**
     * 传统文件上传方法实现
     * @return
     */
    @RequestMapping("/myupload")
    public String myupload(HttpServletRequest request){

        return "success";
    }
}
