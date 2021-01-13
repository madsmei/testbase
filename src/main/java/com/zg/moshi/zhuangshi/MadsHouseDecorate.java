package com.zg.moshi.zhuangshi;

/**
 * @Description: 装饰者抽象类，它规定了装饰的流程，也就是接口的调用过程，具体的扩展 由该类子类去实现
 * @Date 2020/3/26
 * @Version V1.0
 * @Author Mads
 **/
public abstract class MadsHouseDecorate implements MadsHouse {

    private MadsHouse house;

    private MadsHouseDecorate() {
    }

    public MadsHouseDecorate(MadsHouse house) {
        this.house = house;
    }

    @Override
    public void people() {
        house.people();
    }

    @Override
    public void goods() {
        house.goods();
    }
}
