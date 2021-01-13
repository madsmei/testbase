package com.abc.util;

/**
 * @Author Mads
 * @Date 2020/8/24 10:42
 */
public class SaoCaoZuo {

    /****
     *  判断奇偶
     * @Author Mads
     * @Date 2020/8/24
     * @return 偶数=0，奇数=1
    **/
    public static int checkOddEven(int num) {
        return num & 1;
    }

    /****
     *  如果为 0，说明 n 是 2 的整数幂
     * @Author Mads
     * @Date 2020/8/24
    **/
    public static int checkNum2(int num) {
        return num & (num - 1);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println("num:"+i+" oddEven:"+checkOddEven(i));
            System.out.println("num:"+i+" checkNum2:"+checkNum2(i));
        }
    }
}
