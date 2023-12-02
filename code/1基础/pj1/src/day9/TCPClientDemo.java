package day9;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClientDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建Socket套接字对象
        // 三次握手建立连接，如果连接不上会报错
        Socket socket = new Socket("127.0.0.1", 10008);
        // 2.从连接通道中获取输出流
        OutputStream os = socket.getOutputStream();
        os.write("你好!!".getBytes());    // utf-8 字节流
        // 3.释放资源
        // 四次挥手断开连接，保证连接通道内的数据传送完毕
        socket.close();
    }
}
