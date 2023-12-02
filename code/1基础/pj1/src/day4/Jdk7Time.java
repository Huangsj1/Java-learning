package day4;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Jdk7Time {
    public static void main(String[] args) throws ParseException {
        /*
        // 一、Data 日期
        // 1.创建对象获取当前时间
        Date d1 = new Date();
        System.out.println(d1);

        // 2.创建对象指定时间（中国东8区时间）
        Date d2 = new Date(0L);
        System.out.println(d2);

        // 3.修改时间(毫秒)
        d2.setTime(1000L);
        System.out.println(d2);

        // 4.获取时间的毫秒值
        long time = d2.getTime();
        System.out.println(time);
         */

        System.out.println("----------------------");

        /*
        // 二、SimpleDateFormat 格式化日期
        // 1.创建对象
        SimpleDateFormat sdf = new SimpleDateFormat();
        // 直接打印是引用/地址
        System.out.println(sdf);
        Date d = new Date(0L);
        // 3.格式化，将日期对象转换成字符串
        String str = sdf.format(d);
        System.out.println(str);

        // 2.指定格式创建对象
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String str2 = sdf2.format(d);
        System.out.println(str2);

        // 4.解析，将字符串转换成日期对象
        String str3 = "2023-11-27 16:04:00";
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf3.parse(str3);
        System.out.println(date);
        System.out.println(date.getTime());
         */

        // 三、Calendar 日历类，方便修改日期字段
        // 月份范围[0,11]，星期从周日开始作为第一天
        // 1.构建对象
        Calendar c = Calendar.getInstance();
        System.out.println(c);

        // 2.修改日历代表的时间
        Date d = new Date(0L);
        c.setTime(d);
        System.out.println(c);

        // 3.取日期某个字段的值
        // 0：纪元，1：年，2：月，3：一年的第几周，4：一月的第几周，5：一个月的第几天...
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        int week = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(year + "," + month + "," + day + ", 星期" + week);

        // 4.设置字段值
        c.set(Calendar.YEAR, 2000);
        int year2 = c.get(Calendar.YEAR);
        System.out.println(year2);

        // 5.增加/减少某个字段值
        c.add(Calendar.YEAR, 5);
        c.add(Calendar.YEAR, -2);
        int year3 = c.get(Calendar.YEAR);
        System.out.println(year3);
    }
}
