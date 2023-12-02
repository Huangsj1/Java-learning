package day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;

public class FunctionRefDemo {
    public static void main(String[] args) {
        // 一、方法引用的是使用
        Integer[] arr = {3, 5, 1, 2, 9};

        // 1.匿名内部类
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        System.out.println(Arrays.toString(arr));

        // 2.Lambda表达式
        Arrays.sort(arr, (o1, o2) -> { return o2 - o1; });
        System.out.println(Arrays.toString(arr));

        // 3.方法引用（需要指定前面的类名,::是方法引用符）
        Arrays.sort(arr, FunctionRefDemo::sortMethod);
        System.out.println(Arrays.toString(arr));

        // 二、引用静态方法：类名::静态方法名
        System.out.println("----------静态方法----------");
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "1", "2", "34", "167");
        list.stream().map(Integer::parseInt).forEach(s -> System.out.println(s));

        // 三、引用成员方法：其他类对象名::成员方法名，this::成员方法名，super::成员方法名
        System.out.println("----------成员方法----------");
        list.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("1") && s.length() > 2;
            }
        }).forEach(s -> System.out.println(s));
        list.stream().filter(new StringOperation1()::stringJudge).forEach(s -> System.out.println(s));

        // 四、引用构造方法：类名::new
        // 使用与上面方法引用一样，需要该类的构造函数具有和该函数式接口有相同的参数、方法体
        System.out.println("----------构造方法----------");
        // 1)直接用类的构造方法
        list.stream().map(StringOperation2::new).forEach(s -> System.out.println(s));
        // 2)引用数组的构造方法
        String[] sl = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(sl));

        // 五、类名引用成员方法：类名::成员方法
        // 这种方法只能用抽象方法中第一个参数类型中的成员方法，且成员方法的参数需要和抽象方法中的从第二个参数开始后的参数一致
        System.out.println("----------类名::成员方法----------");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "a", "b", "cd", "abc");
        list2.stream().map(String::toUpperCase).forEach(s -> System.out.println(s));
    }

    public static int sortMethod(int o1, int o2) {
        return o1 - o2;
    }
}

class StringOperation1 {
    public boolean stringJudge(String s) {
        return s.startsWith("1") && s.length() > 2;
    }
}

class StringOperation2 {
    int val;

    public StringOperation2() {
    }

    public StringOperation2(String str) {
        this.val = Integer.parseInt(str);
    }

    public StringOperation2(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "StringOperation2{" +
                "val=" + val +
                '}';
    }
}