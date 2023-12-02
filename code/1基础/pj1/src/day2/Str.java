package day2;

public class Str {
    public static void main(java.lang.String[] args) {
        /*
        // 一、String
        // 1.直接赋值方法创建字符串对象（在堆中存储字符串常量）
        String s1 = "abc";
        System.out.println(s1);

        // 2.new方式创建（堆中创建String对象，里面包含指向堆中常量空间中的字符串常量）
        // 参数可以为字符串常量、字符数组、字节数组、字符串对象
        String s2 = new String();
        System.out.println("默认" + s2 + "为空");
        String s3 = new String("aaa");
        System.out.println(s3);

        char[] chs = {'b', 'c', 'd'};
        String s4 = new String(chs);
        System.out.println(s4);

        byte[] bytes = {97, 98, 99};
        String s5 = new String(bytes);
        System.out.println(s5);

        String s6 = new String(s5);
        System.out.println(s6);

        // Scanner输入的字符串是new出来的String对象，所以不能直接和字符串常量""比较，需要用String里的方法比较
        System.out.println("-----------");
        String userName = "hello";
        Scanner sc = new Scanner(System.in);
        String inputName = sc.next();
        System.out.println(userName == inputName);
        System.out.println(userName.equals(inputName));
         */


        // 二、StringBuilder，可以看作是一个容器，存放可变的字符串，能加速字符串拼接
        StringBuilder sb = new StringBuilder("abc");
        System.out.println(sb);
        // 1.添加元素
        sb.append(1);
        sb.append(true).append(false);
        System.out.println(sb);

        // 2.反转
        sb.reverse();
        System.out.println(sb);

        // 3.获取长度、容量
        int len = sb.length();
        System.out.println(len);
        int cap = sb.capacity();
        System.out.println(cap);

        // 4.转换成String类型
        String sb2s = sb.toString();
        System.out.println(sb2s);

        /*
        // 三、StringJoiner，也可以看作是容器，能指定间隔符号、[开始符号、结束符号]，用于拼接
        // 只能拼接字符序列/字符串
        StringJoiner sj = new StringJoiner("---", "[", "]");
        // 1.添加元素
        sj.add("aaa").add("bbb").add("ccc");
        System.out.println(sj);

        // 2.获取长度
        int sjLen = sj.length();
        System.out.println(sjLen);

        // 3.转成String
        String sj2s = sj.toString();
        System.out.println(sj2s);
        */
    }
}
