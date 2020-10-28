package com.joe.enmulearn;

import com.joe.enmulearn.Color;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ckh
 * @create 9/16/20 8:52 AM
 */
public class EnumTest {

    public static void main(String[] args) {
        // 调用 values()
        Color[] colors = Color.values();

        // 迭代枚举
        for (Color col : colors) {
            // 查看索引
            System.out.println(col + " at index " + col.ordinal());
            System.out.println(col.getIndex());
            System.out.println(col.getDeclaringClass());
        }

        // 使用 valueOf() 返回枚举常量，不存在的会报错 IllegalArgumentException
        System.out.println(Color.valueOf("RED"));

        boolean b = Color.BLUE.equals(Color.GREEN);
        System.out.println(b);
        // System.out.println(Color.valueOf("WHITE"));
    }

    @Test
    public void testEqualsMethod() {
        // false
        System.out.println(Color.RED.equals(""));
        // false
        System.out.println(Color.RED.equals("red color"));
        // false
        System.out.println(Color.RED.equals("RED"));

        // false
        System.out.println(Color.RED.equals(Color.BLUE));
        // 同一个对象
        // true
        System.out.println(Color.RED.equals(Color.RED));
        // 实际是两个 String
        // true
        System.out.println(Color.RED.toString().equals("RED"));
        // 实际是两个 String
        // true
        System.out.println(Color.RED.getName().equals("red color"));
        // hashCode
        System.out.println("Color.RED.hashCode = " + Color.RED.hashCode());


        System.out.println(Color.BLUE.compareTo(Color.RED));

    }


    @Test
    public void testTime(){
        // 格林威治1970年1月1日0点0分0秒
        Date date1 = new Date(0);
        System.out.println("格林威治0时的时间戳：" + date1.getTime());
        System.out.println("格林威治0时的中国时区时间：" + date1);
        // 当前时间戳
        Date date2 = new Date();
        System.out.println("date2的时间戳：" + date2.getTime());
        System.out.println("date2表示的中国时区时间:" + date2);
        // 设置时区为美国洛杉矶时区
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println("date2表示的美国洛杉矶时区时间：" + date2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置为美国纽约时区
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println("date2表示的美国纽约时区时间：" + sdf.format(date2));
    }
}
