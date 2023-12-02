package day2;

public class object_use {
    public static void main(String[] args) {
        Object obj = new Object();
        obj.setName("111");
        // obj.price = 222;     报错，访问了私有private属性
        obj.setPrice(222);
        obj.buy();
        Object obj2 = new Object("555", 666);
        System.out.println(obj2.getName());
        System.out.println(obj2.getPrice());
        System.out.println(obj2);
    }
}
