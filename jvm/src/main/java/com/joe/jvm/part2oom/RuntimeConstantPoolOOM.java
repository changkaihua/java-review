package com.joe.jvm.part2oom;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk 1.6
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * <p>
 * OpenJDK 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
 * <p>
 * jdk 7 起, 常量池从方法区移到了堆中, 所以要限制堆的大小
 * <p>
 * base jdk 1.8
 *
 * @author ckh
 * @create 10/12/20 4:58 PM
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
//        test1();
        test2();

    }

    /**
     * jdk 7 起, 常量池从方法区移到了堆中, 所以要限制堆的大小
     * -Xms6m -Xmx6m
     * int 和 short 报错有区别 -_-
     */
    private static void test1() {
        List<String> list = new ArrayList<String>();

        // OutOfMemoryError: GC overhead limit exceeded
        // int i=0;

        // OutOfMemoryError: Java heap space
        short i = 0;

        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    private static void test2() {
        String str1 = new StringBuilder("computer").append("software").toString();
        // true
        System.out.println(str1.intern() == str1);

        String aa = "computersoftware";
        System.out.println(aa.intern() == str1);

        // java 标准库在 JVM 启动过程中加载的部分中有类引用“java”字符串字面量，这个字面量在初次引用的时候会被intern，加入到字符串常量池中
        // 在 1.8 中，sun.misc.Version.launcher_name 的值为 openjdk, 该值会自动加载，所以 intern 时不是第一次出现， 为 false
        String str2 = new StringBuilder("ja").append("va").toString();
        // false
//        System.out.println(str2.intern() == str2);

//        String str3 = new StringBuilder("computersoftware").toString();
//        System.out.println(str3.intern() == str3);

//        String str4 = new StringBuilder("computer").toString();
//        System.out.println(str4.intern() == str4);

        String str5 = new String("asdfas");
        System.out.println(str5.intern() == str5);

        String str6 = "asdfasdf";
        System.out.println(str6.intern() == str6);



        String a = "a";
        String b = "b";
        String c = a + b;

        System.out.println(c.intern() == c);

    }
}
