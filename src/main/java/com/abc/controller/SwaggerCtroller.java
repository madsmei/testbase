package com.abc.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/f")
public class SwaggerCtroller {

    @Autowired
    private RestTemplate restTemplate;


    @ApiOperation(value="获取用户信息",notes = "根据用户id获取用户基本信息")
    @ApiImplicitParam(name = "id",value = "用户id" ,required = true,dataType = "String")
    @RequestMapping(value = "/firstApi/{id}",method = RequestMethod.GET)
    public Map<String,Object> hellohtml(@PathVariable String id){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","mads");
        map.put("uid",id);

        return map;
    }


}
