package com.zg.moshi.zhuangshi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 这个是 被装饰者，
 * @Date 2020/3/26
 * @Version V1.0
 * @Author Mads
 **/
public class Mads2013 implements MadsHouse {

    @Override
    public void people() {

        System.out.println("============2013年家里只有我和老婆两个人==================");
    }

    @Override
    public void goods() {
        System.out.println("============2013年家里只有一辆自行车==================");
    }
}
