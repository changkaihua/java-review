package com.joe.jvm.part2oom;

/**
 * @author ckh
 * @create 10/12/20 4:49 PM
 */
public class JavaVmStackOOM {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    /**
     * -Xss2M
     * 操作系统会假死
     */
    public static void main(String[] args) {
        JavaVmStackOOM oom = new JavaVmStackOOM();
        oom.stackLeakByThread();
    }
}
