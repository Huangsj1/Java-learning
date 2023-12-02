package day9;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Method;

public class ReflectClass {
    public static void main(String[] args) throws ClassNotFoundException {
        // 一、获取字节码文件class对象
        // 1.全类名：包名+类名
        // 最常用的
        Class clazz1 = Class.forName("day9.MyClass");

        // 2.类名.class
        Class clazz2 = MyClass.class;

        // 3.对象名.getClass()
        // 已有这个类的对象后可用
        Class clazz3 = new MyClass().getClass();

        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);

        // 二、反射获取构造方法
        System.out.println("----------构造方法----------");
        Constructor[] con = clazz1.getDeclaredConstructors();
        for (Constructor constructor : con) {
            System.out.println(constructor);
        }
        // 获取构造方法的各种信息：权限、参数等
        int modifiers = con[1].getModifiers();
        System.out.println(modifiers);
        Parameter[] parameters = con[1].getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter);
        }

        // 三、反射获取成员变量
        System.out.println("----------成员变量----------");
        Field[] fields = clazz1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        // 获取成员变量的各种信息：权限、名称、数据类型等
        int modifiers1 = fields[0].getModifiers();
        System.out.println(modifiers1);
        String name = fields[0].getName();
        System.out.println(name);
        Class<?> type = fields[0].getType();
        System.out.println(type);

        // 四、反射获取成员方法
        System.out.println("----------成员方法----------");
        // getMethods()包含父类的公共方法
        Method[] methods = clazz1.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("-----");
        // getDeclaredMethods不能获取父类的
        Method[] methods1 = clazz1.getDeclaredMethods();
        for (Method method : methods1) {
            System.out.println(method);
        }
        // 可以获得方法得名字、修饰符、参数名
        System.out.println(methods1[0].getName());
    }
}

class MyClass {
    public String name;
    private int age;

    public MyClass() {
    }

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "MyClass{name = " + name + ", age = " + age + "}";
    }
}