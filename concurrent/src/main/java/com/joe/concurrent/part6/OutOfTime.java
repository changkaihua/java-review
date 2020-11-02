package com.joe.concurrent.part6;

import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * OutOfTime
 * <p/>
 * Class illustrating confusing Timer behavior
 *
 * @author Brian Goetz and Tim Peierls
 */

public class OutOfTime {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        System.out.println("1  " + System.currentTimeMillis());
        timer.schedule(new ThrowTask(), 3);
        SECONDS.sleep(1);
        System.out.println("2  " + System.currentTimeMillis());

        // IllegalStateException: Timer already cancelled.
        timer.schedule(new ThrowTask(), 3);
        SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("3  " + System.currentTimeMillis());
            throw new RuntimeException();
        }
    }
}
