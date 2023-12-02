package day5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> m = new HashMap<>();

        // 1.put添加元素
        System.out.println("-----------put----------");
        m.put("aaa", "AA");
        m.put("bbb", "BB");
        String s1 = m.put("ccc", "CC");
        System.out.println(m);
        // 1)相同键值会覆盖，返回被覆盖的值(无则返回null)
        String s2 = m.put("bbb", "B");
        System.out.println(m);
        System.out.println(s1);
        System.out.println(s2);

        // 2.clear清除元素
        /*System.out.println("-----------clear----------");
        m.clear();
        System.out.println(m);*/

        // 3.remove根据键值删除元素，返回值
        System.out.println("-----------remove----------");
        String s3 = m.remove("aaa");
        System.out.println(m);
        System.out.println(s3);

        // 4.containsKey查询是否包含键
        System.out.println("-----------containsKey----------");
        boolean b1 = m.containsKey("aaa");
        boolean b2 = m.containsKey("bbb");
        System.out.println(b1);
        System.out.println(b2);

        // 5.containsValue查询是否包含键
        System.out.println("-----------containsValue----------");
        boolean b3 = m.containsValue("aaa");
        boolean b4 = m.containsValue("CC");
        System.out.println(b3);
        System.out.println(b4);

        // 6.isEmpty判断是否为空
        System.out.println("-----------isEmpty----------");
        boolean b5 = m.isEmpty();
        System.out.println(b5);

        // 7.size查看长度
        System.out.println("-----------size----------");
        int len = m.size();
        System.out.println(len);

        // 8.get根据键值获取元素
        System.out.println("-----------get----------");
        String s4 = m.get("bbb");
        System.out.println(s4);


        System.out.println("===================三种遍历方式===============");

        Map<String, String> m2 = new HashMap<>();
        m2.put("aaa", "AA");
        m2.put("bbb", "BB");
        m2.put("ccc", "CC");

        // 1.遍历键值
        System.out.println("----------1.遍历所有键----------");
        // 1)获得所有键，放到一个单列集合中
        Set<String> keys = m2.keySet();
        // 2)遍历键值来获取对应值
        for (String key : keys) {
            System.out.println(key + ": " + m2.get(key));
        }

        // 2.键值对遍历，然后分别获取键和值
        System.out.println("----------2.遍历所有键值对----------");
        // 1)获取所有键值对
        Set<Map.Entry<String, String>> entries = m2.entrySet();
        // 2)遍历所有键值对对象，获取每个键和值
        for (Map.Entry<String, String> entry : entries) {
            String k = entry.getKey();
            String v = entry.getValue();
            System.out.println(k + ": " + v);
        }

        // 3.forEach遍历，利用匿名内部类/Lambda表达式输出
        System.out.println("----------3.forEach遍历----------");
        // 1).匿名内部类
        System.out.println("匿名内部类遍历-----");
        m2.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println(key + ": " + value);
            }
        });
        // 2).Lambda表达式
        System.out.println("Lambda表达式------");
        m2.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
