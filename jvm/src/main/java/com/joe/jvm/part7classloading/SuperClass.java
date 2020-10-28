package com.joe.jvm.part7classloading;

/**
 * @author ckh
 * @create 10/15/20 10:49 AM
 */
public class SuperClass {

    static {
        System.out.println("super class init");
    }

    public static int value = 123;
}
