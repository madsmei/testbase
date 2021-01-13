package com.abc.demo;

import com.abc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Description: 这里是用乐观锁 加 编程事务解决商品超卖  伪代码
 * @Date 2020/4/2
 * @Version V1.0
 * @Author Mads
 **/
public class HappyLockDemo {

    @Autowired
    TransactionTemplate transactionTemplate;

    @Transactional
    public void doProductSale() {


        int exeuce = transactionTemplate.execute(status -> {
            //1.获取锁,也就是先查询，可以加上库存的判断。如果已经是0了  直接返回
            List<User> list = new ArrayList<>();

            //这个操作是 直接把对应的version+1，先站住
            int i = new Random().nextInt(1);

            if (i > 0) {
                //处理商品处理逻辑。扣库存。。。

            }
            return i;
        });

        if (exeuce == 0) {
            //继续抢够商品
            doProductSale();
        }
    }


}
