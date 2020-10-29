package com.joe.concurrent.part3;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                /*
                    如果一直被忽略yield, 则死循环
                 */
                Thread.yield();
            }
            // 指令重拍导致 ready 在 number 之前写入并发生线程切换, 则显示0
            // 大概率是 42
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}