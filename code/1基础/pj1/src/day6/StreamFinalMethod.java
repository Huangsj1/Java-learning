package day6;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class StreamFinalMethod {
    public static void main(String[] args) {
        // 下面展示了一些终结方法，返回void，一般在结束的时候用

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a-1", "ab-12", "c-3", "ab-12");

        // 1.forEach 遍历
        System.out.println("----------forEach----------");
        list.stream().forEach(s -> System.out.println(s));

        // 2.count 统计
        System.out.println("----------count----------");
        System.out.println(list.stream().count());

        // 3.toArray 转成数组
        System.out.println("----------toArray----------");
        // 1)用object类型数组接收
        Object[] arr1 = list.stream().toArray();
        System.out.println(Arrays.toString(arr1));
        // 2)指定特定类型数组
        String[] arr2 = list.stream().toArray(new IntFunction<String[]>() {
            // 泛型里面是需要的类型，函数的参数是数组的大小，返回值是具体类型的数组
            @Override
            public String[] apply(int value) {
                return new String[value];
            }
        });
        System.out.println(Arrays.toString(arr2));
        String[] arr3 = list.stream().toArray(value -> new String[value]);
        System.out.println(Arrays.toString(arr3));

        // 4.collect 收集到集合中（List、Set、Map）
        // 1)收集到List
        System.out.println("----------collect To List----------");
        List<String> res1 = list.stream().filter(s -> s.startsWith("a")).collect(Collectors.toList());
        System.out.println(res1);
        // 2)收集到Set(会自动去重)
        System.out.println("----------collect To Set----------");
        Set<String> res2 = list.stream().filter(s -> s.startsWith("a")).collect(Collectors.toSet());
        System.out.println(res2);
        // 2)收集到Map(不会自动去重，需要保证不重复)，需要定义键的规则和值的规则
        System.out.println("----------collect To Map----------");
        // 这里有两个函数接口，第一个是通过流中的值返回得到键，第二个是通过流中的值返回得到值
        Map<String, Integer> res3 = list.stream()
                .distinct()
                .collect(Collectors.toMap(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.split("-")[0];
            }
        }, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s.split("-")[1]);
            }
        }));
        System.out.println(res3);
        Map<String, Integer> res4 = list.stream()
                .distinct()
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[1])));
        System.out.println(res4);
    }
}
