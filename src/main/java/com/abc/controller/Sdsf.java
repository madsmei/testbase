package com.abc.controller;

import com.abc.entity.TestEntity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Date 2020/3/13
 * @Version V1.0
 * @Author Mads
 **/
@Service
public class Sdsf implements Adsfd,ApplicationContextAware {

    private ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void aaaaaa(){
        TestEntity bean = context.getBean(TestEntity.class);

        System.out.println("--:"+bean.toString());

        bean.setName("aaaaa");
        bean.setAdress("vvvv");
        
    }

    @Override
    public void bbbb() {
        TestEntity bean = context.getBean(TestEntity.class);

        System.out.println("--:"+bean.toString());

        bean.setName("bbbb");
        bean.setAdress("bbbb");
    }

}
