package com.zg.moshi.shipeiqi;

/**
 * @Description: 对象适配器   三相电 转成二项电
 * @Date 2020/4/20
 * @Version V1.0
 * @Author Mads
 **/
public class Adapter implements NetToUsb {

    /**
     * 使用委托来完成适配
     */
    private WangXian twoPower;

    public Adapter(WangXian twoPower) {
        this.twoPower = twoPower;
    }

    @Override
    public void powerByThree() {
        System.out.println("借助组合适配器转化二项电");
        twoPower.powerByTwo();
        ;
    }
}
