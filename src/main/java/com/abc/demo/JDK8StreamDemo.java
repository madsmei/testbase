package com.abc.demo;

import com.abc.entity.User;
import com.google.common.collect.ImmutableList;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*******
 * jdk1.8中年的Stream的用法(流式编程)
 *
 * list.stream() 返回一个顺序流
 * list.parallelStream() 返回一个并行流  底层使用了Fork/Join框架
 *
 * 1.中间操作（返回一个流)，不调用终止操作 是不会执行中间操作的（惰性求值）链式调用的核心
 *    有状态操作。distinct,sorted,limit,skip
 *    无状态操作 unordered,filter,map,mapToInt,maoToLang,flatMaotoInt,peek,
 *   ps:所以判断流操作是否有状态的判断标准，就是看是否需要知道先前的数据历史。前后数据是否有依赖关系来判断，lamda一个参数数无状态。2个参数有状态
 * 2.终止(结束)操作
 *  非短路操作，foreach,foreachOrdered,collect,toArray,reduce,min,max,count
 *  短路操作,findFirst,findAny,allMatch,anyMatch,NoneMatch
 */
public class JDK8StreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);
        list.add("mads4");
        list.add("mads1");
        list.add("mads3");
        list.add("mads5");
        list.add("1234567");

        List<User> userList = new ArrayList<>(5);
        userList.add(new User("mads", 19));
        userList.add(new User("xiaolin", 40));
        userList.add(new User("huazi", 32));
        userList.add(new User("mi", 19));


        System.out.println(list.stream().filter(i -> i.equals("mads3")).findFirst().get());

        //map还有三个应用于具体类型方法，分别是：mapToInt，mapToLong和mapToDouble。这三个方法也比较好理解，比如mapToInt就是把原始Stream转换成一个新的Stream，
        //这个新生成的Stream中的元素都是int类型。这三个方法可以免除自动装箱/拆箱的额外消耗
        //map 吧对象转换成其他对象，只是数据转换 就用Map,如果有数据的操作急用flatMap
        userList.stream().map(user -> user.getName()).forEach(name -> System.out.println(name));

        //汇总年龄求和  Collectors. 提供了很多,例如 sum avg,count ,max,min...
        userList.stream().collect(Collectors.summarizingInt(User::getAge));

        //分组
        Map<Integer, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::getAge));
        MapUtils.verbosePrint(System.out, "按照年龄分组", collect);

        System.out.println("第一个元素：" + list.stream().findFirst().get());
        System.out.println("????：" + list.stream().findAny().get());
        //必须所有的都要满足条件才返回true。
        boolean m = list.stream().allMatch(s -> s.startsWith("m"));
        System.out.println("---1--" + m);
        //相当于 上面条件上面加了一个 ！，做了一个结果反转
        boolean m1 = list.stream().noneMatch(s -> s.startsWith("x"));
        System.out.println("-2--" + m1);

        //只要有一个满足条件就返回true
        boolean m2 = list.stream().anyMatch(s -> s.startsWith("x"));
        System.out.println("-3--" + m2);

        //找出 数字类型的，可以简单理解是一些列的过滤器。。
        String result = list.stream().filter(i -> StringUtils.isNumeric(i))
                //长度>4的
                .filter(i -> i.length() > 4)
                //转换成小写，
                .map(i -> i.toLowerCase())
                //排重
                .distinct()
                //排序
                .sorted(Comparator.naturalOrder())

//                .sorted(Comparator.comparing(User::getAge))
                //跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补。（就是分页了）
                .skip(2)
                //限制数量
                .limit(10)
                //增加分隔符collect 收集结果转成Collectors中的各种,并增加分隔符
                .collect(Collectors.joining("-幽灵-"));
        System.out.println("--4-" + result);

        int[] intArray = {1, 2, 3};
        IntStream stream = Arrays.stream(intArray);
        System.out.println("数组创建流--");

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("值创建流--");
		//求和1，reduce()就是对流进行两两操作，并返回一个对象
		integerStream.reduce(0, (a, b) -> a + b);
		//求和2
		integerStream.reduce(0, Integer::sum);

        Stream<String> StringStream1 = Stream.of("my name is mads".split(" "));
        //.boxed() 装箱操作。
        StringStream1.flatMap(s -> s.chars().boxed());
        //peek 用于debug，是个中间操作，功能和 forEach （是个终止操作）很像, 比forEach更快
        StringStream1.peek(System.out::printf);
        //打印的结果是顺序的。如果使用forEach 打印的结果是乱序的
        StringStream1.forEachOrdered(System.out::printf);
        //增加分隔符
        StringStream1.reduce((s1, s2) -> s1 + "|" + s2);
        //计算单词 总长度，reduce 有个默认值，这里是 0
        StringStream1.map(s -> s.length()).reduce(0, (s1, s2) -> s1 + s2);
        //isPresent() 查看是否查到结果
        StringStream1.filter(value -> value != "").findAny().isPresent();

        List<User> user_money_List = new ArrayList<>(5);
        user_money_List.add(new User("mads", ImmutableList.of("1元", "3元")));
        user_money_List.add(new User("xiaolin", ImmutableList.of("4元", "3元")));
        user_money_List.add(new User("huazi", ImmutableList.of("5元", "7元")));
        user_money_List.add(new User("mi", ImmutableList.of("9元", "13元")));

        //接收一个Function函数作为参数，将流中的每个值都转换成另一个流，然后把所有流连接成一个流。
        // flatMap也有三个应用于具体类型的方法，分别是：flatMapToInt、flatMapToLong、flatMapToDouble，其作用于map的三个衍生方法相同
        //简单理解就是 合并对象中A中的一个B属性（是个集合）最终得到所有A中的所有B属性集合
        user_money_List.stream().flatMap(user -> user.getMoney().stream()).forEach(System.out::println);

        //ints是生成无限流。所以一定要有limit来限制
        new Random().ints().filter(i -> i > 100).limit(10);


    }

}
