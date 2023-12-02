package day7;

import java.io.*;

public class BufferedByteStreamDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("src\\day7\\r.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("src\\day7\\w.txt"));
        // 2.读写文件
        // 1)一个字节一个字节地循环
        /*int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }*/
        // 2)字节数组读取
        byte[] bytes = new byte[100];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        // 3.释放资源（不用释放FileInputStream和FileOutputStream中的，会在内部释放）
        bos.close();
        bis.close();
    }
}
