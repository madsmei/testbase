package com.zg.suanfa;

/**
 * @Description: 二分查找
 *
 * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
 * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字
 * @Date 2020/3/9
 * @Version V1.0
 * @Author Mads
 **/
public class ErFen {

    /******
     * @param array  有序数组
     * @param a   目标数字
     * @return 返回的是 数据下标位置
     */
    public static int biSearch(int []array,int a) {

        int lo = 0;
        int hi = array.length - 1;
        int mid;

        while (lo <= hi) {

            mid = (lo + hi) / 2;//中间位置

            if (array[mid] == a) {
                System.out.println("找到了返回--"+array[mid]);
                return mid + 1;
            } else if (array[mid] < a) { //向右查找
                System.out.println("--数据在右边，继续找--");
                lo = mid + 1;
            } else { //向左查找
                System.out.println("--数据在左边，继续找--");
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = {6,12,20,30,43,52,66,74,80,91,106,117};

        System.out.println("找到的结果-->："+biSearch(array, 12));
    }
}
