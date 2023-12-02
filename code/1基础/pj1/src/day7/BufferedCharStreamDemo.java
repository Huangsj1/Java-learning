package day7;

import java.io.*;

public class BufferedCharStreamDemo {
    public static void main(String[] args) throws IOException {
        // 1.创建对象
        BufferedReader br = new BufferedReader(new FileReader("src\\day7\\r.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\day7\\w.txt"));
        // 2.整行读写
        String line;
        while ((line = br.readLine()) != null) {
            // 读取整行，不读取换行符
            System.out.println(line);
            // 写没有换行的一行内容，同时添加一行跨平台换行
            bw.write(line);
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
