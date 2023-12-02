package day4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternPatch {
    public static void main(String[] args) {
        String str = "Java经历了很多版本，包括Java8和Java11";

        // 1.获取正则表达式对象
        Pattern p = Pattern.compile("Java\\d{0,2}");
        // 2.获取文本匹配器对象
        Matcher m = p.matcher(str);
        // 循环获取匹配字符串
        while(m.find()) {
            // 3.查找匹配字符串
            String s = m.group();
            System.out.println(s);
        }

        System.out.println("---------------------------");
        String str2 = "abbbbbbbbbbb";
        // 加?到+/*的后面变成非贪婪爬取
        Pattern p2 = Pattern.compile("ab+?");
        Matcher m2 = p2.matcher(str2);
        while(m2.find()) {
            String s2 = m2.group();
            System.out.println(s2);
        }

        String str3 = "abbbbb";
        String regex = "[ab]b+";
        str3.matches(regex);
    }
}
