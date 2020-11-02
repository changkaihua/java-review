package com.joe.concurrent.part5;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 3个线程轮流打印ABC
 *
 * @author ckh
 * @create 11/2/20 10:13 AM
 */
public class TestABC {
    /**
     * using ReentrantLock and condition
     */
    static class PrintABCMethod1 {
        private static Lock lock = new ReentrantLock();
        private static Condition A = lock.newCondition();
        private static Condition B = lock.newCondition();
        private static Condition C = lock.newCondition();

        private static int count = 0;

        public static void printA() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    //注意这里是不等于0，也就是说在count % 3 为 0 之前，当前线程一直阻塞状态
                    while (count % 3 != 0) {
                        // A释放lock锁
                        A.await();
                    }
                    System.out.println("A");
                    count++;
                    B.signal(); // A执行完唤醒B线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public static void printB() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    // B释放lock锁，当前面A线程执行后会通过B.signal()唤醒该线程
                    while (count % 3 != 1) {
                        B.await();
                    }
                    System.out.println("B");
                    count++;
                    C.signal();// B执行完唤醒C线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public static void printC() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2) {
                        // C释放lock锁
                        C.await();
                    }
                    System.out.println("C");
                    count++;
                    // C执行完唤醒A线程
                    A.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    static class PrintABCMethod2{

    }

    public static void main(String[] args) {

        new Thread(PrintABCMethod1::printA).start();
        new Thread(PrintABCMethod1::printB).start();
        new Thread(PrintABCMethod1::printC).start();

    }
}
