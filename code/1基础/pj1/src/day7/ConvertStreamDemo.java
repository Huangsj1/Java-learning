package day7;

import java.io.*;
import java.nio.charset.Charset;

public class ConvertStreamDemo {
    public static void main(String[] args) throws IOException {
        // 一、转换流/文件字符流按指定字符编码读取
        System.out.println("==========read==========");
        // 1.JDK11以前，转换流读取
        InputStreamReader isr = new InputStreamReader(new FileInputStream("src\\day7\\gb.txt"), "GBK");
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }
        isr.close();

        // 2.JDK11以后，可以用FileReader读取
        System.out.println();
        System.out.println();
        FileReader fr = new FileReader("src\\day7\\gb.txt", Charset.forName("GBK"));
        int ch2;
        while ((ch2 = fr.read()) != -1) {
            System.out.print((char) ch2);
        }
        fr.close();

        // 二、转换流/文件字符流按指定字符编码写文件
        System.out.println("==========write==========");
        // 1.JDK11以前，转换流写入
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("src\\day7\\gbw.txt"), "GBK");
        osw.write("你好\n你好");
        osw.close();
        // 2.JDK11以后，FileWriter写入
        FileWriter fw = new FileWriter("src\\day7\\gbw.txt", Charset.forName("GBK"), true);
        fw.write("你好啊\n你好啊");
        fw.close();
    }
}
