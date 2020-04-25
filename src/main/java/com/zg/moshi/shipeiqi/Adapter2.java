package com.zg.moshi.shipeiqi;

/**
 * @Description: 类适配器   三相电 转成二项电
 * @Date 2020/4/20
 * @Version V1.0
 * @Author Mads
 **/
public class Adapter2 extends WangXian implements NetToUsb {
    @Override
    public void powerByThree() {
        System.out.println("借助继承适配器转化二项电");
        this.powerByTwo();
    }
}
