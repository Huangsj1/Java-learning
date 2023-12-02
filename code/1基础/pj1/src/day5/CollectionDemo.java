package day5;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionDemo {
    public static void main(String[] args) {
        // Collection是一个接口，需要创建其实现类的对象
        //  下面包括List和Set两大集合
        //    List是有序、可重复、有索引
        //    Set 是无序、不重复、无索引

        Collection<String> coll = new ArrayList<>();
        // 1.add 添加元素
        //      List可重复，永远返回true
        //      Set不可重复，不存在则true，存在则false
        System.out.println("----------add----------");
        coll.add("aaa");
        coll.add("bbb");
        boolean res = coll.add("aaa");
        System.out.println(res);
        System.out.println(coll);

        // 2.clear 清空元素
        /*
        System.out.println("----------clear----------");
        coll.clear();
        System.out.println(coll);
        */

        // 3.remove 删除元素，只能通过元素对象进行删除
        // 删除元素存在则删除成功，返回true，否则返回false
        System.out.println("----------remove----------");
        boolean res2 = coll.remove("aaa");
        boolean res3 = coll.remove("efg");
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(coll);

        // 4.contains 判断元素是否包含
        // 底层依赖是调用equals方法来判断的，所以具体类型需要重写equals方法
        System.out.println("----------contains----------");
        boolean res4 = coll.contains("aaa");
        System.out.println(res4);

        // 5.isEmpty 判断集合是否为空
        System.out.println("----------isEmpty----------");
        boolean res5 = coll.isEmpty();
        System.out.println(res5);

        // 6.size 集合的长度
        System.out.println("----------size----------");
        int sz = coll.size();
        System.out.println(sz);
    }
}
