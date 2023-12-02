package day9;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建ServerSocket对象
        ServerSocket ss = new ServerSocket(10008);
        // 2.监听客户端的连接
        Socket socket = ss.accept();
        // 3.从连接通道中获取输入流读取数据
        InputStream is = socket.getInputStream();
        // 将字节流转换成字符流
        InputStreamReader isr = new InputStreamReader(is);
        int b;
        while ((b = isr.read()) != -1) {
            System.out.print((char) b);
        }
        // 4.释放资源
        socket.close();
        ss.close();
    }
}
