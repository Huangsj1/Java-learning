package day3;

public class Final {
    public static void main(String[] args) {
        // 3.final修饰变量 -> 常量
        // 1)不能改变基本类型的值
        final int a = 10;
        // a = 100;

        // 2)不能改变引用类型的地址值，但是对象内部的值依旧可变
        final Animal ani = new Animal("abc");
        // ani = new Animal("def");
        ani.name = "bcd";
        System.out.println(ani.name);
    }
}

// 1.final修饰类不能被继承
final class fu1 {}

/*
// 会报错，不能继承final类
class zi1 extends fu1 {}
*/


class fu2 {
    // 2.final修饰方法
    public final void show() {
        System.out.println("父类的show方法");
    }
}

class zi extends fu2 {
    /*
    // 下面会报错,final定义的函数不能被重写
    @Override
    public void show() {
        System.out.println("子类的show方法");
    }*/
}