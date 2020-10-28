package com.joe.jvm.part2oom;

/**
 * @author ckh
 * @create 10/12/20 4:33 PM
 */
public class JavaVmStackSOF1 {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * -Xss236k
     */
    public static void main(String[] args) throws Throwable {
        JavaVmStackSOF1 stackSOF = new JavaVmStackSOF1();
        try {
            stackSOF.stackLeak();
        } catch (Throwable a) {
            System.out.println("stack length:" + stackSOF.stackLength);
            throw a;
        }
    }
}
