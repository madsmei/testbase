package com.abc.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/******
 * 阿里开发规范里，对 map遍历的建议
 *
 *
 * mads  2020-1-16
 */
public class ALiMapForEachDemo {
    public static void main(String[] args) {
        Map<String,String> map_test = new HashMap<>();
        map_test.put("m1", "m1value");
        map_test.put("m2", "m2value");

        /******
         * 1.建议使用 map.entrySet(),这个只循环一次就可以吧值遍历出来
         * 2.而 map.keySet()要经过先转成key的列表在根据key找到value ,效率上不如 （1）
         * TODO //代办的逻辑】
         * FIXME //表示此代码是错误的不能正常执行。需要重写逻辑
         */
        for (Map.Entry entry : map_test.entrySet()) {
            System.out.println(entry.getKey() + "  " +entry.getValue());
        }

        /****
         *  1.8的方式
         *  Set 等集合中也都加入了 forEach的方法
         *
         */
        map_test.forEach((key,value) -> {
            System.out.println("1.8遍历 key:"+key+" value:"+value);
        });

        //JUC下的处理随机数的类
        int random_int = ThreadLocalRandom.current().nextInt(1, 5);


        //try-with-resource  优雅的关闭资源
        try(FileInputStream inputStream = new FileInputStream(new File("test"))) {

        }catch (IOException e) {
        }finally {
        }



    }
}
