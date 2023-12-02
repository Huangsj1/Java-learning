package day6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamMidMethod {
    public static void main(String[] args) {
        // 下面展示了一些中间方法，原来的Stream流只能用一次，所以不要用变量赋值，用链式编程
        // 修改流中的数据不会改变原来的数据

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "ab", "c", "d", "ab");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "111", "222");

        // 1.filter 过滤元素
        System.out.println("----------filter----------");
        list.stream().filter(new Predicate<String>() {
            // 一个函数式接口，返回true表示留下数据，false表示不留
            @Override
            public boolean test(String s) {
                return s.startsWith("a");
            }
        }).forEach(s -> System.out.println(s));
        list.stream().filter(s -> s.startsWith("a")).forEach(s -> System.out.println(s));

        // 2.limit 获取前几个数据
        System.out.println("----------limit----------");
        list.stream().limit(3).forEach(s -> System.out.println(s));

        // 3.skip 跳过前几个数据
        System.out.println("----------skip----------");
        list.stream().skip(2).forEach(s -> System.out.println(s));

        // 4.distinct 去重（依赖hashCode和equals方法）
        System.out.println("----------distinct----------");
        list.stream().distinct().forEach(s -> System.out.println(s));

        // 5.concat 合并两个流
        System.out.println("----------concat----------");
        Stream.concat(list.stream(), list2.stream()).forEach(s -> System.out.println(s));

        // 6.map 转换流中的数据类型
        System.out.println("----------map----------");
        list2.stream().map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        }).forEach(s -> System.out.println(s));
        list2.stream().map(s -> Integer.parseInt(s)).forEach(s -> System.out.println(s));
    }
}
