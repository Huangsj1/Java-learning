package day7;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1.创建对象
        // 文件不存在就报错
        FileInputStream fis = new FileInputStream("src\\day7\\a.txt");
        // 2.读取数据(读到末尾没有了就返回-1)
        // 1)一次读取一个字节，读到的是ASCII码对应的数字
        int v1 = fis.read();
        // 2)循环读取所有字节
        int tmp;
        while ((tmp = fis.read()) != -1) {
            System.out.print((char)tmp);
        }
        System.out.println();
        // 3)读取所有字节到字节数组中，返回读取到的长度，若为空返回-1，且数组不读取
        byte[] b1 = new byte[40];
        int v2 = fis.read(b1);
        byte[] b2 = fis.readAllBytes();
        int v3 = fis.read();
        // 3.释放资源
        fis.close();
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(Arrays.toString(b1));
        System.out.println(Arrays.toString(b2));
        System.out.println(v3);
    }
}
