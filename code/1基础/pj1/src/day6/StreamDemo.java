package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        // 1.单列集合获取Stream流
        System.out.println("----------单列集合----------");
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "dd");
        Stream<String> stream1 = list.stream();
        System.out.println(stream1);
        list.stream().forEach(s -> System.out.println(s));

        // 2.双列集合无法直接获取Stream流，需要获取键值对的单列集合再获取流
        System.out.println("----------双列集合----------");
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("aaa", 1);
        hm.put("bbb", 2);
        hm.keySet().stream().forEach(s -> System.out.println(s));
        hm.entrySet().stream().forEach(s -> System.out.println(s));

        // 3.数组获取Stream流(基本数据类/引用都可以)
        System.out.println("----------数组----------");
        int[] arr1 = {1, 2, 3};
        String[] arr2 = {"a", "b", "c"};
        Arrays.stream(arr1).forEach(s -> System.out.println(s));
        Arrays.stream(arr2).forEach(s -> System.out.println(s));

        // 4.同种数据类型的离散数据获取Stream流
        //  也可以传数组，但是只能传引用类型的数组，传基本数据类型的数组会将数组当成一个元素
        System.out.println("----------离散数据----------");
        Stream.of(1, 2, 3).forEach(s -> System.out.println(s));
        Stream.of(arr1).forEach(s -> System.out.println(s));
        Stream.of(arr2).forEach(s -> System.out.println(s));
    }
}
