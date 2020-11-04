package com.joe.concurrent.part7;

/**
 * join() 的使用场景
 * 在很多情况下，主线程创建并启动子线程，如果子线程中要进行大量的耗时运算，主线程将可能早于子线程结束。
 * 如果主线程需要知道子线程的执行结果时，就需要等待子线程执行结束了。
 * 主线程可以sleep(xx),等待xx秒, 但这样的xx时间不好确定，因为子线程的执行时间不确定，join()方法比较合适这个场景
 *
 * @author ckh
 * @create 11/4/20 11:52 AM
 */
public class ThreadJoinTest {
    static class BThread extends Thread {
        public BThread() {
            super("[BThread] Thread");
        }
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " start.");
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(threadName + " loop at " + i);
                    Thread.sleep(1000);
                }
                System.out.println(threadName + " end.");
            } catch (Exception e) {
                System.out.println("Exception from " + threadName + ".run");
            }
        }
    }

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " start.");
        BThread bt = new BThread();
        try {
            // join方法必须在线程start方法调用之后调用才有意义。
            // 这个也很容易理解：如果一个线程都没有start，那它也就无法同步了
            bt.start();
            // 只有bt线程执行完毕后, 才会执行main线程, 利用 wait() 实现
            bt.join();
//            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Exception from main");
        }
        System.out.println(threadName + " end!");
    }
}
