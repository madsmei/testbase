package com.abc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hello1")
public class AsyncCtroller {

    @Autowired
    private RestTemplate restTemplate;


    /******
     * 使用业务场景: 对于有的请求业务处理流程可能比较耗时，比如长查询，远程调用等，主线程会被一直占用，
     * 而tomcat线程池线程有限，处理量就会下降
     *
     * servlet3.0以后提供了对异步处理的支持，springmvc封装了异步处理，满足用户请求后，主线程很快结束，
     * 并开启其它线程处理任务，并将处理结果响应用户，而主线程就可以接收更多请求
     * 原理简介:对于一次请求，比如front/test
     *
     * 1,springmvc开启副线程处理业务(将Callable 提交到 TaskExecutor)
     * 2,DispatcherServlet和所有的Filter退出web容器的线程，但是response 保持打开状态
     * 3,Callable返回结果，SpringMVC将请求front/test重新派发给容器(再重新请求一次front/test)，恢复之前的处理；
     * 4,DispatcherServlet重新被调用，将结果返回给用户
     * @return
     */
    @RequestMapping(value = "/hello1")
    public Callable<String> hellohtml() throws ExecutionException, InterruptedException {

        Callable<String> res = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("子线程");
                return "我是结果啊";
            }
        };
        System.out.println("--------主线程----------------");

        return res;
    }


}
