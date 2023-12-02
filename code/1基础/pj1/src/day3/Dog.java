package day3;

public class Dog extends Animal{
    // 子类会继承所有的父类变量，且同名也不会重写
    public String sex = "Dog sex";

    public Dog() {
        // 默认会调用 super(); 但是如果有调用父类的构造函数就不会添加
        System.out.println("子类的无参构造");
    }

    public Dog(String name) {
        // 主动调用父类的有参构造
        super(name);
        System.out.println("子类的有参构造，name=" + this.name);
    }

    public void bark() {
        System.out.printf("Dog %s is barking...\n", name);
        // System.out.println(age);     报错，不能访问父类的私有属性
    }

    @Override
    public void eat() {
        super.eat();
        System.out.printf("Dog %s is eating...\n", name);
    }

    public void showFaSex() {
        System.out.println(super.sex);
    }
}
