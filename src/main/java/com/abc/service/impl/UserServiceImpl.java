package com.abc.service.impl;

import com.abc.dao.UserMapper;
import com.abc.entity.User;
import com.abc.service.UserService;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
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

    //设置事务为shardingjdbc的XA方案,他默认的事务使用的是本地事务
    @ShardingTransactionType(TransactionType.XA)
    @Override
    public void save(Long id, String name, int age) {

        User user = new User();
        user.setName(name);
        user.setAge(age);

        userMapper.insert(user);
    }
}
