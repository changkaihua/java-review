package com.joe.concurrent.part1;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author ckh
 * @create 10/28/20 3:10 PM
 */
public class UnsafeSequenceTest {

    @Test
    public void getNext() {

        UnsafeSequence unsafeSequence = new UnsafeSequence();

        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 101; i++) {
                next = unsafeSequence.getNext();
                Thread.yield();
            }
            System.out.println("1=" + next);
        }).start();

        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
                Thread.yield();
            }
            System.out.println(next);
        }).start();

        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(next);
        }).start();

        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();
        new Thread(() -> {
            int next = 0;
            for (int i = 0; i < 100; i++) {
                next = unsafeSequence.getNext();
//                Thread.yield();
            }
            System.out.println(next);
        }).start();


    }

}