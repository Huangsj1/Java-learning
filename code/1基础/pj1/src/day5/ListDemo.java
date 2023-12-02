package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListDemo {
    public static void main(String[] args) {
        /*
            List系列独有的方法（与索引相关）
         */

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");

        // 1.add 指定索引位置添加元素（索引后的往后移）
        System.out.println("----------add----------");
        list.add(1, "ccc");
        System.out.println(list);

        // 2.remove 删除指定索引元素，并返回该元素
        System.out.println("----------remove----------");
        String str = list.remove(0);
        System.out.println(str);
        System.out.println(list);

        // 3.set 修改指定索引的元素，返回被修改元素
        System.out.println("----------set----------");
        String str2 = list.set(0, "aaa");
        System.out.println(str2);
        System.out.println(list);

        // 4.get 获得指定索引的元素
        System.out.println("----------get----------");
        String str3 = list.get(0);
        System.out.println(str3);

        // 5.增加的遍历方法
        // 1)普通for循环遍历
        System.out.println("----------普通for遍历----------");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        // 2）列表迭代器，可以添加元素
        System.out.println("----------列表迭代器遍历且添加元素----------");
        ListIterator<String> it = list.listIterator();
        while(it.hasNext()) {
            String str4 = it.next();
            if ("bbb".equals(str4)) {
                //list.add("eee");      //直接添加会报错
                it.add("qqq");
            }
        }
        System.out.println(list);
    }
}
