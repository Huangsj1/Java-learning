package day9;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ThreadPoolRunnable implements Runnable{

    Socket socket;

    public ThreadPoolRunnable() {}

    public ThreadPoolRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1.保存传输通道中的数据到本地文件
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            String desName = UUID.randomUUID().toString().replace("-", "");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("data\\server\\" + desName + ".txt"));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            // 将缓冲区数据写到文件中，防止结束了还没有写
            bos.flush();
            // 2.回写数据
            // 字节流 -> 字符流 -> 缓冲字符流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("上传成功");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
