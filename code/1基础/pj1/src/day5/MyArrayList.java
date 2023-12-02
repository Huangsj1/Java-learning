package day5;

import java.util.Arrays;

public class MyArrayList<E> {
    /*
        E表示不确定类型
     */

    final int MAX_SIZE = 10;
    int size;
    Object[] obj = new Object[MAX_SIZE];

    public boolean add(E e){
        if (size >= MAX_SIZE) {
            System.out.printf("达到最大长度%d，无法添加\n", MAX_SIZE);
            return false;
        }
        obj[size++] = e;
        return true;
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            System.out.println("index out of range");
            return null;
        }
        return (E)obj[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(obj);
    }
}
