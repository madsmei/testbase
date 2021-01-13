package com.abc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mads
 * @Description 测试 亚马逊  DynamoDB,Spring-data 提供
 * @Date 10:17 2020/5/7
 **/
@RestController
@RequestMapping("/dynamodb")
public class DynamoDBCtroller {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/add")
    public String add() throws ExecutionException, InterruptedException {


        return "ok";
    }


}
