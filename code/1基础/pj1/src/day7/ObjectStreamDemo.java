package day7;

import java.io.*;

public class ObjectStreamDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 序列化流，写对象到文件中
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\day7\\obj.txt"));
        SerialObject so = new SerialObject("小明", 10, "男");
        oos.writeObject(so);
        oos.close();

        // 反序列流，读取对象到程序中
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\day7\\obj.txt"));
        SerialObject so2 = (SerialObject) ois.readObject();
        ois.close();
        System.out.println(so2);
    }
}

// 1.需要继承Serializable才能进行序列化
class SerialObject implements Serializable {
    @Serial
    private static final long serialVersionUID = -2806177308890199995L;
    // 2.需要定义整个序列化变量，这样当修改了类成员的时候，不会导致读取错误
    private String name;
    private int age;
    // 3.transient瞬态，能够不让其序列化到文件中
    private transient String sex;


    public SerialObject() {
    }

    public SerialObject(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    /**
     * 获取
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String toString() {
        return "name = " + name + ", age = " + age + ", sex = " + sex + "}";
    }
}