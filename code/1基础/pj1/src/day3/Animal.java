package day3;

public class Animal {
    public String name;
    public String sex = "Animal sex";
    private int age;

    public Animal() {
        // 这里显示调用其他构造函数，调用了之后虚拟机就不会添加super();
        this("无名");
        System.out.println("父类的无参构造");
    }

    public Animal(String name) {
        this.name = name;
        System.out.println("父类的有参构造 name=" + this.name);
    }

    public void eat() {
        System.out.printf("Animal %s is eating...\n", name);
    }

    public static void showStatic() {
        System.out.println("Anima Static Method");
    }
}
