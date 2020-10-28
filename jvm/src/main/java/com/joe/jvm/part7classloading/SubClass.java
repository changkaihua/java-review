package com.joe.jvm.part7classloading;

/**
 * @author ckh
 * @create 10/15/20 10:54 AM
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("Sub class init");

    }
}
