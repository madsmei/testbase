package com.abc.controller;


import com.abc.listener.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/test")
public class TestCtroller {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoginService loginService;


    /******
     * 监听器 测试
     */
    @RequestMapping(value = "/listenerTest")
    public String listenerTest() {
        loginService.Login("mads","123456");

        return "ok";
    }


}
