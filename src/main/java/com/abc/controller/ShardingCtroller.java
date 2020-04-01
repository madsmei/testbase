package com.abc.controller;


import com.abc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sharding")
public class ShardingCtroller {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add")
    @ResponseBody
    public String add()  {

        for (int i = 1; i <= 50; i++) {
            userService.save((long)i,"潇潇"+i,i);
            System.out.println("添加完成---"+i);
        }
        return "ok";
    }



}
