package day7;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        // 相对路径从当前模块开始
        // 一、FileOutputStream的使用
        // 1.创建对象
        // 1）参数是文件路径 / 文件对象
        // 2）文件不存在会创建文件，但是要保证父级路径存在
        // 3）文件已存在会清除里面的内容
        FileOutputStream fos = new FileOutputStream("src\\day7\\a.txt");
        System.out.println(fos);
        // 2.写出数据
        // 参数是整数 / 字节类型，但是写道文件中的是ASCII字符
        fos.write(97);
        // 3.释放资源（否则会一直占用资源直到释放）
        // fos.close();

        // 二、三种写数据方式
        // 1.一次写一个字节
        fos.write(13);  // 换行
        fos.write(98);
        // 2.一次写一个byte数组大小的数据
        fos.write(13);  // 换行
        byte[] bytes = {97, 98, 99};
        fos.write(bytes);
        // 3.一次写byte数组部分数据
        fos.write(13);  // 换行
        fos.write(bytes, 1, 2);
        //fos.close();

        // 三、换行写和续写
        // 1.换行：windows换行符为 \r\n，Linux为 \n，Mac为 \r
        String str = "\r\nabc\r\n123";
        byte[] bytes1 = str.getBytes();
        System.out.println(Arrays.toString(bytes1));
        fos.write(bytes1);
        fos.close();
        // 2.续写：创建对象的时候第二个参数选择true打开续写
        FileOutputStream fos1 = new FileOutputStream("src\\day7\\a.txt", true);
        fos1.write(bytes1);
        fos1.close();
    }
}
