package com.joe.test;

/**
 * @author ckh
 * @since 2020/12/17
 */
public class Test extends Father {
    public static void main(String[] args) throws Exception {
        new Father();
    }

    static {
        System.out.println("static");
    }

    {
        System.out.println("not static");
    }
}
