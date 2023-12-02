package day9;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolServer {
    public static void main(String[] args) throws IOException {
        // 服务端：接收客户端上传的文件，上传完毕后给出反馈

        // 创建线程池对象
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,  // 核心线程数量
                16, //线程池总大小
                60, // 空闲时间
                TimeUnit.SECONDS,   // 空闲时间（单位）
                new ArrayBlockingQueue<>(2),    // 等待队列
                Executors.defaultThreadFactory(),       // 线程工厂，线程池如何创建线程对象
                new ThreadPoolExecutor.AbortPolicy()    // 阻塞队列
        );

        // 1.创建对象并绑定端口
        ServerSocket ss = new ServerSocket(10009);
        // 2.多线程循环等待客户端发送数据
        while (true) {
            // 等待客户端连接
            Socket socket = ss.accept();
            // 开启一条线程执行一个用户的任务
            pool.submit(new ThreadPoolRunnable(socket));
        }
        // 3.释放资源
        // ss.close(); 到达不了，因为前面死循环
    }
}
