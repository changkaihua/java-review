package com.joe.jvm.part6;

/**
 * @author ckh
 * @create 10/14/20 10:58 AM
 */
public class TestClass {
    private static int m;

    public static int inc() {
        return m + 1;
    }

    void onlyMe(Object o) {
        synchronized (o) {
            doSomething();
        }

    }

    public void doSomething() {
        int i = 8;
    }

}
