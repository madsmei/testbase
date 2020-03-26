package com.abc.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/******
 * 本方法记录 抢红包的 实现
 * @author mads
 */
public class RedPackageUtil1 {

    /*******
     * 二倍均值法
     *
     * 原理
     * 剩余红包金额M，剩余人数N，那么：每次抢到金额=随机(0，M/N*2)
     * 保证了每次随机金额的平均值是公平的
     * 假设10人，红包金额100元
     * 第一人：100/10*2=20，随机范围(0,20)，平均可以抢到10元
     * 第二人：90/9*2=20，随机范围(0,20)，平均可以抢到10元
     * 第三人：80/8*2=20，随机范围(0,20)，平均可以抢到10元
     * 以此类推，每次随机范围的均值是相等的
     * 缺点：除了最后一次，任何一次抢到的金额都不会超过人均金额的两倍，并不是任意的随机
     */
    public static List<Integer> divideRedPackage(Integer totalAmount,
                                                 Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();

        //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
        //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
        Integer restAmount = totalAmount * 100;

        Integer restPeopleNum = totalPeopleNum;

        Random random = new Random();

        for (int i = 0; i < totalPeopleNum - 1; i++) {

            // 随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }


    /*******
     * 线段切割法
     * 原理
     * 把红包总金额想象成一条很长的线段，而每个人抢到的金额，则是这条主线段所拆分出的若干子线段。
     * 当N个人一起抢红包的时候，就需要确定N-1个切割点。
     * 因此，当N个人一起抢总金额为M的红包时，我们需要做N-1次随机运算，以此确定N-1个切割点。
     * 随机的范围区间是[1，100* M）。当所有切割点确定以后，子线段的长度也随之确定。
     * 这样每个人来抢红包的时候，只需要顺次领取与子线段长度等价的红包金额即可
     *
     * 原文链接：https://blog.csdn.net/q957967519/article/details/84661761
     */
    private static List<Integer> divide(double money, int n) {
        //验证参数合理校验
        //为了使用random.nextInt(Integer)方法不得不先把红包金额放大100倍，最后在main函数里面再除以100
        //这样就可以保证每个人抢到的金额都可以精确到小数点后两位
        int fen = (int) (money * 100);
        if (fen < n || n < 1) {
            System.out.println("红包个数必须大于0，并且最小红包不少于1分");
        }
        List<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(fen);
        //红包个数和板砖个数的关系
        while (boards.size() <= n) {
            int index = new Random().nextInt(fen - 1) + 1;
            if (boards.contains(index)) {
                //保证板子的位置不相同
                continue;
            }
            boards.add(index);
        }

        //计算每个红包的金额，将两个板子之间的钱加起来
        Collections.sort(boards);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boards.size() - 1; i++) {
            Integer e = boards.get(i + 1) - boards.get(i);
            list.add(e);
        }
        return list;

    }
    public static void main(String[] args) {
//        List<Integer> accountList = divideRedPackage(50, 1000);
        List<Integer> accountList = divide(50, 7);
        BigDecimal count = new BigDecimal(0);
        for (Integer amount : accountList) {
            //将抢到的金额再除以100进行还原
            BigDecimal tmpcount = new BigDecimal(amount).divide(new BigDecimal(100));
            count = count.add(tmpcount);
            System.out.println("抢到金额：" + tmpcount);

        }
        System.out.println("total=" + count);
    }
}
