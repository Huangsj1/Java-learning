package day7;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建字符读取对象关联到本地文件
        FileReader fr = new FileReader("src\\day7\\r.txt");
        // 2.读取字符
        // 1)一次读一个字符
        // 一个字节一个字节地读取，遇到中文就一次读取多个字节，并将一次读取到的字节解码转换成十进制
        //  解码后的十进制可以转成char类型得到英文/中文字符（char两个字节够表示中文了）
        /*int ch;
        while ((ch = fr.read()) != -1) {
            System.out.println();
            System.out.print((char)ch);
        }*/
        // 2)一次读取多个字符
        //  等价于空参read() + 强制类型转换成char + 存到数组里
        char[] chars = new char[2];
        int len;
        while ((len = fr.read(chars)) != -1) {
            System.out.print(new String(chars, 0, len));
        }
        // 3.释放资源
        fr.close();
    }
}
