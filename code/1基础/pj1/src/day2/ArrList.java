package day2;

import java.util.ArrayList;

public class ArrList {
    public static void main(String[] args) {
        // 1.创建集合对象
        // ArrayList<int> al1 = new ArrayList<>(); 会报错，集合里面不能包含基本数据类型
        ArrayList<String> al = new ArrayList<>();
        System.out.println(al);

        // 2.增加
        al.add("abc");
        al.add("bbb");
        al.add("ccc");
        System.out.println(al);

        // 3.删除
        al.remove("bbb");
        boolean rmf = al.remove("fff");     // 不存在则返回false
        String rm = al.remove(1);
        System.out.println(rmf);
        System.out.println(rm);
        System.out.println(al);

        System.out.println("-------");

        // 4.修改
        String ud = al.set(0, "aaa");
        System.out.println(ud);
        System.out.println(al);

        // 5.查询
        String gt = al.get(0);
        System.out.println(gt);

        // 6.获取长度
        int len = al.size();
        System.out.println(len);

        System.out.println("-----");


        // 基本数据类型要写成包装类：Byte, Short, Integer, Long, Character, Float, Double, Boolean
        ArrayList<Integer> al2 = new ArrayList<>();
        al2.add(123);
        System.out.println(al2);
    }
}
