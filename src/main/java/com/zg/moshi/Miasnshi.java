package com.zg.moshi;

import java.io.OutputStream;

/**
 * @Description: TODO
 * @Date 2020/2/24
 * @Version V1.0
 * @Author Mads
 **/
public class Miasnshi {
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");

        int c = 66;
        String d = "D";
//        open(c,a, b,d);
        open1(a.toString(), b.toString());
        System.out.println(a + " " + b + " " + c + " " + d);

        dowhile();
        complicatedexpression_r();

        System.out.println("计算结果：" + foo(5));
    }

    static int foo(int n) {

        if (n < 2) return n;
        int a = foo(n - 1) + foo(n - 2);
        System.out.println("----a:" + a);
        return a;
    }

    /*****
     * 这个方法看似是对 ++i和i++ 的知识。其实是 do while的用法的检验。
     */
    static void dowhile() {
        int x = 0;
        int y = 10;
        do {
            y--;
            ++x;
        } while (x < 5);
        System.out.print(x + "," + y);
    }

    /*****
     * 考察 && 和& 的区别。&&具有短路功能，如果左侧为false,也就不去后面比较了，所以这里 后面的判断再长 也没用，
     */
    static void complicatedexpression_r() {
        int x = 20, y = 30;
        boolean j;
        j = x > 50 && y > 60 || x > 50 && y < -60 || x < -50 && y > 60 || x < -50 && y < -60;
        System.out.println(j);
    }


    /*****
     *  考察 java 值传递的理解。
     *  这里方法中的a,b相当于是副本，所以在这里面做的= 的操作都是不成功的。
     *  a.annend()的操作因为操作的是值 所以能改变值。另外IDE也会帮我们来确定。我们发现方法里的b,c,d的赋值操作是灰色的，
     *  这也说明这几个参数其实没有被使用，只是 局部的变量而已，
     * @param c
     * @param a
     * @param b
     * @param d
     */
    private static void open(int c, StringBuffer a, StringBuffer b, String d) {
        a.append(b);
        b = a;
        c = 666;
        d = "dddd";
    }

    private static void open1(String a, String b) {
        a = a + b;
        b = a;
    }
}
