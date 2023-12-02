package day2;

public class operator {
    public static void main(String[] args){
        /*
        // 1.算数运算
        // byte、char、short运算后的结果为int类型
        // 当取值范围大的赋值给小的需要显示“强制转换”
        byte b = -1;
        System.out.println(b);
        b = 0x7f;
        System.out.println(b);
        b = (byte)(b + 1);
        System.out.println(b);

        // 2.字符串(只有)相加：从左往右，加号左右有字符串就直接拼接（无论是什么类型的值都直接拼接）
        String str = "123" + 123;
        System.out.println(str);
        str = 1 + 99 + "hm";
        System.out.println(str);
        System.out.println("中" + "abc" + true);
         */

        // 3.逻辑运算符: & | ^ !；短路逻辑运算符：&& ||
        // 常用 && || !
        System.out.println(true & false);
        System.out.println(true | false);
        System.out.println(false ^ false);      // 相同为false
        System.out.println(!true);
        System.out.println(true && false && true);
        System.out.println(false || true || true);
        // 4.位运算符
        System.out.println(11 & 7);
        System.out.println(11 | 6);
        System.out.println(11 ^ 6);             // 1011 ^ 0110 = 1101
        // System.out.println(!11);             报错，!不能用于int
        System.out.println(~11);                // 按位取反 其再取反+1即为数值12，所以结果是-12
    }
}