package com.zg.moshi.shipeiqi;

/**
 * @Description: 结构型 模式
 * <p>
 * 将一个类的接口转换成客户希望的另外一个接口。适配器模式 使的原本由于接口不兼容不能一起工作的类又可以在一起工作了
 * @Date 2020/4/20
 * @Version V1.0
 * @Author Mads
 **/
public class Computer {
    /**
     * 期望的三项供电接口
     */
    private NetToUsb threePower;

    public Computer(NetToUsb threePower) {
        this.threePower = threePower;
    }


    public static void main(String[] args) {
        // ============================ 继承方式的适配器使用 类适配器 =====================================
        NetToUsb threePower1 = new Adapter2();
        Computer noteBook1 = new Computer(threePower1);
        noteBook1.recharge();
        noteBook1.work();

        // ============================ 组合方式的适配器使用 对象适配器 =====================================
        // 现在只有二项供电
        WangXian twoPower = new WangXian();
        NetToUsb threePower = new Adapter(twoPower);
        Computer noteBook = new Computer(threePower);
        // 1. 充电
        noteBook.recharge();
        // 2. 工作
        noteBook.work();
    }

    public void work() {
        System.out.println("笔记本电脑开始工作!");
    }

    public void recharge() {
        // 使用三项充电
        threePower.powerByThree();
    }
}
