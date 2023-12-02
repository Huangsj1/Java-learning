package day4;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arr);

        // 1.toString
        System.out.println("----------toString-----------");
        String s1 = Arrays.toString(arr);
        System.out.println(s1);

        // 2.binarySearch
        System.out.println("----------binarySearch-----------");
        int p1 = Arrays.binarySearch(arr, 3);
        System.out.println(p1);

        // 3.copyOf
        System.out.println("----------copyOf-----------");
        int[] arr2 = Arrays.copyOf(arr, 3);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = Arrays.copyOf(arr, 10);
        System.out.println(Arrays.toString(arr3));

        // 4.CopyOfRange
        System.out.println("----------CopyOfRange-----------");
        int[] arr4 = Arrays.copyOfRange(arr, 0, 4);
        System.out.println(Arrays.toString(arr4));

        // 5.fill
        System.out.println("----------fill-----------");
        Arrays.fill(arr3, 33);
        System.out.println(Arrays.toString(arr3));

        // 6.sort 默认快排升序
        System.out.println("----------sort-----------");
        int[] arr5 = {4, 6, 7, 1, 2, 5, 9, 3};
        Arrays.sort(arr5);
        System.out.println(Arrays.toString(arr5));
        // 定义排序规则来排序,用的是归并排序
        // 1)匿名类
        Integer[] arr6 = {4, 6, 7, 1, 2, 5, 9, 3};
        Arrays.sort(arr6, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(arr6));
        // 2)Lambda表达式
        Integer[] arr7 = {4, 6, 7, 1, 2, 5, 9, 3};
        Arrays.sort(arr7, (o1, o2) -> o1 - o2);
        System.out.println(Arrays.toString(arr7));
    }
}
