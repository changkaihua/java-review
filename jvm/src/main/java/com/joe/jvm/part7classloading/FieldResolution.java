package com.joe.jvm.part7classloading;

/**
 * @author ckh
 * @create 10/16/20 11:32 AM
 */
public class FieldResolution {
    interface Interface0 {
        int A = 0;
    }

    interface Interface1 extends Interface0 {
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        // 如果注释该句, 则会拒绝编译, The field Sub.A is ambiguous
        public static int A= 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }
}
