package day7;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
    public static void main(String[] args) throws IOException {
        // 字符流读取默认用utf-8方式
        // 1.创建写对象连接本地文件
        FileWriter fw = new FileWriter("src\\day7\\w.txt", true);
        // 2.写字符到文件中
        // 1)int类型，十进制根据默认编码方式输出
        fw.write(25105);
        // 2)String类型，默认编码输出
        fw.write("\n你好世界!\n");
        // 3)char[]类型，同上
        char[] chars = {'a', 'b', '你', '好'};
        fw.write(chars);
        // 3.释放资源
        fw.close();
    }
}
