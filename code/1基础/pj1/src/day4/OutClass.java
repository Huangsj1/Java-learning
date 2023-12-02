package day4;

public class OutClass {
    String outName;
    private int age;

    public void show() {
        // System.out.println(inName);      不可以直接使用内部类的成员变量，需要创建对象才可
        System.out.println((new InClass()).inName);
    }

    class InClass {
        String inName;

        public void show() {
            System.out.println(outName + inName);
        }
    }
}
