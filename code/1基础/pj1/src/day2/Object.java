package day2;

public class Object {
    // 1.属性
    String name;
    // private修饰的属性只能在类内使用，其他类中不可使用
    private int price;

    // 3.构造函数
    // 1)默认构造函数
    public Object() {
        System.out.println("默认构造函数");
    }

    // 2)带参构造函数（如果有了带参就需要手动写无参/默认的，否则会报错）
    public Object(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // 2.成员方法
    public void buy() {
        System.out.println("正在购买" + name + ",价格为 " + price);
    }

    public void setName(String name) {
        // name = name      只修改了局部变量参数name，不会改成员属性
        // 成员函数隐藏了this指针
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int p) {
        if (p >= 0) price = p;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        return "Object{name = " + name + ", price = " + price + "}";
    }
}
