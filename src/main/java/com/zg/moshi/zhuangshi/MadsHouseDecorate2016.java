package com.zg.moshi.zhuangshi;

/**
 * @Description: 具体的装饰者
 * @Date 2020/3/26
 * @Version V1.0
 * @Author Mads
 **/
public class MadsHouseDecorate2016 extends MadsHouseDecorate {

    public MadsHouseDecorate2016(MadsHouse house) {
        super(house);
    }

    public void chirlden() {
        System.out.println("2016年生了宝宝");

    }

    public void addGoods() {
        System.out.println("2016年买了沙发");
    }

    @Override
    public void people() {
        //2013年的家庭成员还在
        super.people();

        chirlden();
        ;
    }

    @Override
    public void goods() {
        //2013年的自行车还在
        super.goods();

        addGoods();
    }
}
