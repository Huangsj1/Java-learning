package day7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        // 1.创建对象
        FileInputStream fis = new FileInputStream("src\\day7\\a.txt");
        FileOutputStream fos = new FileOutputStream("src\\day7\\b.txt");

        // 2.循环读写
        // 1)一个字符一个字符地读写，效率低
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        // 2)读取字节数组，效率高
        byte[] bytes = new byte[100];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        // 释放资源，先开的最后关
        fos.close();
        fis.close();
    }
}
