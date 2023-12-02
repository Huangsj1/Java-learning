package day3;

public class AnimalTest {
    public static void main(String[] args) {
        // 1.测试继承
        Dog tidy = new Dog();
        tidy.name = "tidy";
        tidy.eat();
        tidy.bark();
        Dog.showStatic();

        System.out.println("------");
        // 2.有参构造函数的使用
        Dog hsk = new Dog("hsk");

        System.out.println("------");
        // 3.成员变量继承不会重写
        System.out.println(hsk.sex);
        hsk.showFaSex();

        System.out.println("------");
        // 4.多态使用
        Animal ani = new Dog("ani");
        // 1)调用成员变量：编译看左边，运行也看左边
        //  编译时先看左边的类型有无对应的属性，有才能编译成功
        //  运行时用的也是左边类型对应的成员变量
        // 因为成员变量会继承，用的是静态加载
        System.out.println(ani.sex);
        // 2)调用成员函数：编译看左边，运行看右边
        //  编译时先看左边的类型有无对应的属性，有才能编译成功
        //  运行时用的是右边具体对象对应的成员函数（重写的）
        // 因为重写的函数会在虚表中替换，用的是动态加载
        ani.eat();
        // 3)使用子类特定的函数
        // ani.bark();      // 报错，只能使用左边类型的方法，不能用子类特有的方法
        // 但是可以通过强转使用
        ((Dog)ani).bark();
        // 强转之前也可以通过判断来查看对象的类型
        if (ani instanceof Dog) {
            System.out.println("ani is a dog");
            ((Dog) ani).bark();
        }
        // 新特性，可以判断和强转一起
        if (ani instanceof Dog d) {
            d.bark();
        }
    }
}
