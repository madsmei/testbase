package com.abc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloCtroller {

    @Autowired
    private RestTemplate restTemplate;


    /******
     * 测 thymeleaf 模板页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/hellohtml")
    public String hellohtml(ModelMap map){


        map.addAttribute("name","mads");
        //默认找 templaates/hello.html
        return "hello";
    }

    /******
     * 测试 跑出异常 ，自定义错误类拦截异常
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exceptiontest")
    public String exceptiontest() throws  Exception{
        throw  new Exception("11111111");
        //默认找 templaates/hello.html
    }


}
