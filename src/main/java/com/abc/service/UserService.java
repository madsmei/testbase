package com.abc.service;

import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Date 2020/4/1
 * @Version V1.0
 * @Author Mads
 **/
public interface UserService {

    void save(Long id,String name,int age);
}
