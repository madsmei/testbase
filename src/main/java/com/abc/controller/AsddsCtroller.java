package com.abc.controller;


import com.abc.entity.TestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hello2")
public class AsddsCtroller {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Adsfd adsfd;


    @RequestMapping(value = "/hello1")
    public String hellohtml() {


        adsfd.aaaaaa();

        adsfd.bbbb();


        return "k";
    }


}
