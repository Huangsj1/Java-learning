package day1;

import java.util.Scanner;

public class Type {
    public static void main(String[] args) {
        /*
        // 1. 不同类型字面量的使用
        System.out.println(111);
        System.out.println(1.1);
        System.out.println('男');
        System.out.println("String type");
        System.out.println(true);

        // 2. 制表符\t
        System.out.println("abc" + "de");
        System.out.println("abc\t" + "de");


        // 3.变量的使用
        // 变量名由数字、字母、下划线、$构成
        // 不能以数字开头；不能是关键字；区分大小写(关键字都是小写)
        int a = 10, b = 20, c;
        System.out.println(a);
        // int a = 20; 报错，变量定义不能重复
        a = 20;
        System.out.println(a);
        System.out.println(b);
        // System.out.println(c); 报错，变量使用之前需要初始化
        c = 30;
        System.out.println(c);

        // 4.进制
        System.out.println(123);
        System.out.println(011);
        System.out.println(0b10);
        System.out.println(0x1f);

        // 5.基本数据类型
        // 整型：byte 1字节，short 2字节，int 4字节，long 8字节
        byte a = 10;
        short b = 0x1000;
        int c = 0x100000;
        long d = 0x1000000000L;     // long数值需要在后面加L
        long e = (long)c * 10000;   // 如果后面的c不转成long会得到截断后的int再转为long
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        // 小数：float 4字节，double 8字节
        float ff = 1.5F;            // float数值后面要加f
        double dd = 1.5;
        System.out.println(ff);
        System.out.println(dd);
        // 字符：char 2字节(unicode编码)
        char ch = '中';
        System.out.println(ch);
        // 布尔：boolean 1位
        boolean bo = true;
        System.out.println(bo);
         */

        // 6.键盘输入
        // 导包 -> 创建Scanner类 -> 使用读取方法
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入整数：");
        int input = sc.nextInt();
        System.out.println(input);
        System.out.println("请输入需要相加的两数");
        Scanner ts = new Scanner(System.in);
        int a = ts.nextInt();
        int b = ts.nextInt();
        System.out.println(a + b);
    }
}
