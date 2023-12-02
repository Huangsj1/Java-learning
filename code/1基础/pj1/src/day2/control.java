package day2;

import java.util.Random;

public class control {
    public static void main(String[] args) {
        /*
        // 1.if语句
        int a;
        System.out.println(a = 10);
//        下面会报错，因为a=10返回值为10，而if里面需要boolean类型的值
//        if(a = 10) {
//            System.out.println("a = 10");
//        }

        // 2.switch语句
        // 如果没有break，当判断到一个case符合时，会直接执行case内的语句，并且接着往下执行其他case的语句直到遇到brea/结束
        int number = 2;
        switch (number) {
            case 1:
                System.out.println("number: 1");
                // break;
            case 2:
                System.out.println("number: 2");
                // break;
            case 3:
                System.out.println("number: 3");
            default:
                System.out.println("default");
                // break;
        }
        // JDK12新特性，用->来省略break
        switch (number) {
            case 1,2,3,4,5 -> System.out.println("工作日");
            case 6,7 -> System.out.println("休息日");
            default -> System.out.println("没有这个日期");
        }
         */

        // 3.随机数
        Random r = new Random();
        int num = r.nextInt(1, 10); // [1,10)
        System.out.println(num);
    }
}
