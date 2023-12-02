package day3;

public class AbstractDemo {
    public static void main(String[] args) {
        System.out.println("OK?");
        // 1.抽象类不能创建对象
        // Fuu f = new Fuu();
        Zii z = new Zii();
        z.work();
    }
}

// 2.抽象类不一定有抽象方法，有抽象方法的类一定是抽象类
abstract class Fuu {
    public String name;

    // 3.可以有构造方法,供子类初始化对象来初始化父类的变量
    public Fuu() {}

    public Fuu(String name) {
        this.name = name;
    }

    public abstract void work();
}

class Zii extends Fuu{
    public Zii() {}

    public Zii(String name) {
        super(name);
    }

    // 4.子类要么重写抽象父类中的所有抽象方法，要么是抽象类
    public void work() {
        System.out.println("Zi is working...");
    }
}