package day9;

import java.io.*;
import java.net.Socket;

public class ThreadPoolClient {
    public static void main(String[] args) throws IOException {
        // 客户端：将本地文件上传到服务器，接收服务器反馈

        // 1.创建Socket对象，连接服务器
        Socket socket = new Socket("127.0.0.1", 10009);
        // 2.读取本地文件中的数据，写到服务器中
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data\\client\\abc.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        // 将缓冲区的内容传到通信链路上，防止后面结束断开链路
        bos.flush();
        // 3.往服务器写出结束标记
        socket.shutdownOutput();
        // 4.接收服务器的回写数据
        // 字节流 -> 字符流 -> 缓冲字符流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = br.readLine();
        System.out.println(line);
        // 5.释放资源
        socket.close();
    }
}
