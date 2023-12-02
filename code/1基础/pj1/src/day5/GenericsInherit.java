package day5;

import java.util.ArrayList;

public class GenericsInherit {
    public static void main(String[] args) {
        ArrayList<Ye> list1 = new ArrayList<>();
        ArrayList<Fu> list2 = new ArrayList<>();
        ArrayList<Zi> list3 = new ArrayList<>();

        method(list1);
        /* 下面两个会报错
        method(list2);
        method(list3);
         */

        // 2.传入的数据具备继承性
        list1.add(new Ye());
        list1.add(new Fu());
        list1.add(new Zi());

        // 3.泛型通配符
        method2(list1);
        method2(list2);
        method2(list3);

        method3(list1);
        method3(list2);
        method3(list3);

        // method4(list1);  这个会报错，因为Ye是父类不能传递
        method4(list2);
        method4(list3);
    }

    // 1.泛型里面是什么类型，只能传递什么类型，不会继承
    public static void method(ArrayList<Ye> list) {}

    // 3.可以用泛型的通配符指定可以传递哪些继承相关的类型
    //  ?也表示不确定类型，且前面不用加<>
    //  ? extends E 表示传递E或E所有子类类型
    //  ? super E 表示传递E或E所有父类类型
    // 下面二者等价
    public static<E> void method1(ArrayList<E> list) {}
    public static void method2(ArrayList<?> list) {}
    public static void method3(ArrayList<? extends Ye> list) {}
    public static void method4(ArrayList<? extends Fu> list) {}
}

class Ye {}
class Fu extends Ye {}
class Zi extends Fu {}