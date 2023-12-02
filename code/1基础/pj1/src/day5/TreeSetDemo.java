package day5;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        SetStudent s1 = new SetStudent("zhangsan", 10);
        SetStudent s2 = new SetStudent("Lisi", 20);
        SetStudent s3 = new SetStudent("Wangwu", 30);
        SetStudent s4 = new SetStudent("Zhaoliu", 20);
        SetStudent s5 = new SetStudent("Zhaoliu", 20);

        // 1.默认使用里面类型SetStudent的比较规则Comparable接口比较
        TreeSet<SetStudent> ts = new TreeSet<>();

        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);
        ts.add(s5);
        System.out.println(ts);

        System.out.println("--------------------------------------------------");

        // 2.传递比较器Comparator给TreeSet定义比较规则
        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int r1 = o1.length() - o2.length();
                r1 = r1 == 0 ? o1.compareTo(o2) : r1;
                return r1;
            }
        });

        ts2.add("abc");
        ts2.add("abcde");
        ts2.add("bacd");
        System.out.println(ts2);
    }
}
