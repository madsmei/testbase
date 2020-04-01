package com.abc.service;

import com.abc.dao.UserMapper;
import com.abc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Date 2020/4/1
 * @Version V1.0
 * @Author Mads
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(Long id,String name, int age) {

        User user = new User();
        user.setName(name);
        user.setAge(age);

        userMapper.insert(user);
    }
}
