package com.zg.suanfa;

import com.zg.util.ArrayUtil;

import java.util.Arrays;

/**
 * @Description: 冒泡排序算法
 *   （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
 *   （2）这样对数组的第0 个数据到N-1个数据进行一次遍历后，最大的一个数据就 “沉”到数组第N-1 个位置。
 *   （3）N=N-1，如果N 不为0 就重复前面二步，否则排序完成
 * @Date 2020/3/9
 * @Version V1.0
 * @Author Mads
 **/
public class MaoPao {

    public static int[] bubbleSort1(int [] a, int n){

        int i, j;
        for(i=0; i<n; i++){//表示n 次排序过程。
            for(j=1; j<n-i; j++){
                if(a[j-1] > a[j]){//前面的数字大于后面的数字就交换

                    //交换a[j-1]和a[j]
                    int temp;
                    temp = a[j-1];
                    a[j-1] = a[j];
                    a[j]=temp;
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] array = ArrayUtil.randomArray(1, 100, 12);

        for (int i = 0; i <array.length ; i++) {
            System.out.println("--->:"+array[i]);
        }

        int[] b = bubbleSort1(array, array.length);
        for (int i = 0; i <b.length ; i++) {
            System.out.println("====>:"+b[i]);
        }
    }
}
