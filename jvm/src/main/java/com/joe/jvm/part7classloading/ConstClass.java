package com.joe.jvm.part7classloading;

/**
 * @author ckh
 * @create 10/15/20 11:13 AM
 */
public class ConstClass {
    static {
        System.out.println("const class init");
    }

    public static final String HELLO = "hello world";

    public static final int W = 12;
}
