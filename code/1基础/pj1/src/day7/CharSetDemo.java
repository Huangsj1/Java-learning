package day7;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class CharSetDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 1.编码
        String str = "hl你好";
        // 1)默认用utf-8
        byte[] bytes1 = str.getBytes();
        System.out.println(Arrays.toString(bytes1));
        // 2)指定编码方式
        byte[] bytes2 = str.getBytes("GBK");
        System.out.println(Arrays.toString(bytes2));

        // 2.解码
        // 1)默认utf-8
        String str1 = new String(bytes1);
        System.out.println(str1);
        // 2)指定解码方式
        String str2 = new String(bytes1, "GBK");
        System.out.println(str2);
    }
}
