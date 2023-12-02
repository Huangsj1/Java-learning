package day9;

import java.io.IOException;
import java.net.*;

public class UDPSendDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建DatagramSocket对象（快递公司）
        // 参数为绑定的端口号，空参为随机选用一个端口
        DatagramSocket ds = new DatagramSocket();
        // 2.打包数据
        String str = "你好啊!!";
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, address, port);
        // 3.发送数据
        ds.send(dp);
        // 4.释放资源
        ds.close();
    }
}
