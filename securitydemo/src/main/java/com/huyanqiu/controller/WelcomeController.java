package com.huyanqiu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 欢迎页面
 * @ClassName WelcomeController
 * @author: huyanqiu
 * @since: 2019/2/12 17:40
 */
@RestController
public class WelcomeController {

    @RequestMapping("/")
    public String hello() {
        return "welcome";
    }

}
