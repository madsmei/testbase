package com.abc.demo;

import java.util.Optional;

/******
 * JDK1.8中的 Optional 的基础用法
 * 1.去掉繁琐的 if else 个各种判断。。
 */
public class JDK8OptionalDemo {

    public static void main(String[] args) {

        Persion persion = new Persion();
        persion.setName("mads");
        persion.setAge(18);
        persion.setAdress("华北平原一草屋");

        // 使用 Optional.empty() 创建 Optional 对象
//        Optional<Persion> optiona = Optional.empty();
        // 私有化构造函数 不能直接 创建
        // Optional<Persion> optional = new Optional(new Persion());
        // 使用 Optional.of() 创建 Optional 对象
        Optional<Persion> optiona = Optional.of(persion);

        //获取 optional 的值  调用 get()方法 如果 为null 则抛出异常
        System.out.println("----1.--"+optiona.get());

        //检测 optional的值是否为空 如果 为空 则 false
        System.out.println("---2.---"+optiona.isPresent());

        //optional 可以根据  ifPresent 来对 值T  进行 自身的处理
        optiona.ifPresent(p->p.setAge(20));
        System.out.println("---3.--"+optiona.get().getAge());

        //检测一个用户的名称是否 满足 特定条件的时候 ，optional 的filter 方法,如果不满足。则会跑出异常
        Optional<Persion> optionalPersion = optiona.filter(p -> p.getAge() == 20);
        System.out.println("----4.--"+optionalPersion.get().getAdress());

        //Optional 对象中map的使用：返回 User对象的 name属性
        Optional<String> map_name = optiona.map(p -> p.getName());
        System.out.println("--5.--"+map_name.get());

        //
        Persion persion1 = optiona.orElse(new Persion("xiaomi", 20, "华南一草屋"));
        System.out.println(persion1);

        //
        String v = optiona
                .map(Persion::getName)
                .orElse(null);
    }

    static class Persion {

        private String name;
        private int age;
        private String adress;

        @Override
        public String toString() {
            return "Persion{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", adress='" + adress + '\'' +
                    '}';
        }

        public Persion(String name, int age, String adress) {
            this.name = name;
            this.age = age;
            this.adress = adress;
        }

        public Persion() {
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public String getAdress() {
            return adress;
        }
        public void setAdress(String adress) {
            this.adress = adress;
        }
    }
}
