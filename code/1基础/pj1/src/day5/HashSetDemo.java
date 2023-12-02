package day5;

import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> s = new HashSet<>();

        boolean r1 = s.add("aaa");
        boolean r2 = s.add("bbb");
        boolean r3 = s.add("aaa");

        System.out.println(s);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

        System.out.println("===============Testing Hash===============");
        System.out.println("----------hashCode1---------");
        // 1).存入哈希表是根据哈希值来存的
        // 2).相同哈希值才根据equals()来判断是否重复，来决定是否继续存
        //      （不过一般重设了hashCode()，equals()也要重设，所以值相等哈希值一定相等）
        SetStudent h1 = new SetStudent("aaa", 1);
        SetStudent h2 = new SetStudent("aaa", 1);
        System.out.println(h1.hashCode());
        System.out.println(h2.hashCode());

        System.out.println("----------hashCode2---------");
        Set<SetStudent> s2 = new HashSet<>();
        s2.add(h1);
        s2.add(h2);
        System.out.println(h1);
        System.out.println(h2);
        System.out.println(h1.equals(h2));
        System.out.println(s2);
    }
}
