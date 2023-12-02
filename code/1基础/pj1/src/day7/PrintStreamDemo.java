package day7;

import java.io.*;
import java.nio.charset.Charset;

public class PrintStreamDemo {
    public static void main(String[] args) throws IOException {
        // 一、字节打印流
        PrintStream ps = new PrintStream(new FileOutputStream("src\\day7\\ps1.txt"), true, Charset.forName("UTF-8"));
        // 直接打印数据
        ps.println(97);
        ps.print(true);
        ps.println("你好!");
        ps.close();

        // 二、字符打印流
        PrintWriter pw = new PrintWriter(new FileWriter("src\\day7\\pw1.txt"), true);
        // 直接打印数据
        pw.println(97);
        pw.print(true);
        pw.println("你好!");
        pw.close();
    }
}
