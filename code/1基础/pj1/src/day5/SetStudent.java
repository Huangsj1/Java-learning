package day5;

import java.util.Objects;

// 1.实现比较接口比较
public class SetStudent implements Comparable<SetStudent> {
    private String name;
    private int age;

    public SetStudent() {
    }

    public SetStudent(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "MyHashDemo{name = " + name + ", age = " + age + "}";
    }

    // 需要定义equals()和hashCode()供Hash使用
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetStudent that = (SetStudent) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // 1.定义比较接口供TreeSet使用
    @Override
    public int compareTo(SetStudent o) {
        int result = this.getAge() - o.getAge();
        if (result != 0) return result;
        return this.getName().compareTo(o.getName());
    }
}
