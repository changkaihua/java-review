package com.joe.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * @author ckh
 * @create 11/2/20 4:31 PM
 */
public class TestInterrupted {

    @Test
    public void testInterrupt() {

        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread interrupted");
                    break;
                }
                System.out.println("invoking....");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("interrupted with sleep");
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }


    @Test
    public void testFutureGet(){

    }
}
