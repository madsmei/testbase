package com.zg.moshi.zhuangshi;

/**
 * @Description: 装饰器 模式，该类 抽象了角色
 * 装饰模式有几个元素很重要，1、被装饰者。2、抽象装饰者。3、装饰者对象
 * @Date 2020/3/26
 * @Version V1.0
 * @Author Mads
 **/
public interface MadsHouse {

    /****
     * 家庭成员
     */
    public void people();

    /*****
     * 家里 的物件（家具，电器。。）
     */
    public void goods();
}
