package day2;

import java.util.Scanner;

public class IO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1.print不换行
        System.out.print("输入整数：");
        // 遇到空格就会停止，同时保留空格 “123 456” 中只读取了123，缓冲区中保留了” 456“
        int i = sc.nextInt();
        // 2.printf格式化输出，不换行
        System.out.printf("nextInt的值：%d", i);
        System.out.println();

        System.out.print("输入一行字符串：\n");
        String str = sc.nextLine();
        // 3.println换行
        System.out.println("nextLine的值：" + str);
    }
}
