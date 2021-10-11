package com.gmy.edu.controller;

import com.gmy.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @Description:
 * @Author 6
 * @Date 2021/9/14 15:34
 */

@CrossOrigin()
@RestController
@RequestMapping("/eduService/user")
public class LoginController {

    // 登陆的方法：
    @PostMapping("/login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){

        return R.ok().data("roles","[admin]").data("name","gmy")
                .data("avatar","https://img-blog.csdnimg.cn/20200708144550577.JPG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2FtYml0aW9uMDExMTIz,size_16,color_FFFFFF,t_70#pic_center");
    }


}
