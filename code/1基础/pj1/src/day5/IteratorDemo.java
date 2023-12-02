package day5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

public class IteratorDemo {
    public static void main(String[] args) {
        /*
            Collection系列集合的三种遍历方法
         */

        // 一、迭代器遍历
        System.out.println("----------迭代器遍历----------");
        Collection<String> coll = new ArrayList<>();
        coll.add("aaa");
        coll.add("bbb");
        coll.add("ccc");
        coll.add("ddd");
        // 1.获取迭代器对象
        Iterator<String> ite = coll.iterator();
        // 2.循环获取所有元素
        while(ite.hasNext()) {
            // 获取元素并后移
            String str = ite.next();
            System.out.println(str);
            // 删除元素不能用集合的删除方法，需要用迭代器的删除方法
            if(str.equals("bbb")) {
                ite.remove();
            }
        }
        System.out.println(coll);

        // 二、增强for遍历（底层用迭代器）
        //   所有单列集合和数组才可用
        //      快捷键：集合名字.for + Tab
        System.out.println("----------增强for遍历----------");
        for (String s : coll) {
            System.out.println(s);
        }

        // 三、Lambda表达式遍历
        System.out.println("----------Lambda表达式遍历----------");
        // 1.匿名内部类方法：将每个元素依次传递给accept()方法
        System.out.println("匿名内部类遍历-----");
        coll.forEach(new Consumer<String>() {
            @Override
            // s依次代表集合中的每一个数据
            public void accept(String s) {
                System.out.println(s);
            }
        });
        // 2.Lambda表达式
        System.out.println("Lambda表达式遍历-----");
        coll.forEach(s -> System.out.println(s));
    }
}
