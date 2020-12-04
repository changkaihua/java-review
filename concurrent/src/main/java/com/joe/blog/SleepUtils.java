package com.joe.blog;

import java.util.concurrent.TimeUnit;

/**
 * @author ckh
 * @since 2020/12/4
 */
public class SleepUtils {
    public static void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
