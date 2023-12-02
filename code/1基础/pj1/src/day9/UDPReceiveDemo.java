package day9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPReceiveDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建DatagramSocket对象（快递公司）
        // 一定要指定端口，绑定发送方发送到的端口
        DatagramSocket ds = new DatagramSocket(10086);
        // 2.接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        // 接收receive的方法是阻塞的，接收到数据才会继续执行
        System.out.println("waiting...");
        ds.receive(dp);
        System.out.println("Finish receiving...");
        // 3.解析数据包（数据包中包含所有发送方相关的信息）
        byte[] data = dp.getData();
        int len = dp.getLength();
        InetAddress address = dp.getAddress();
        int port = dp.getPort();

        System.out.println(new String(bytes));
        System.out.println("接收到从" + address + "地址的端口号" + port + "发送来的数据");
        System.out.println(new String(data, 0, len));

        // 4.释放资源
        ds.close();
    }
}
