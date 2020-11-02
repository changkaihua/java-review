package com.joe.concurrent.utils;

/**
 * @author ckh
 * @create 11/2/20 9:57 AM
 */
public class LaunderThrowable {
    public static RuntimeException launderThrowable(Throwable cause) {
        if (cause instanceof RuntimeException) {
            return (RuntimeException) cause;
        } else if (cause instanceof Error) {
            throw (Error) cause;
        } else {
            throw new IllegalStateException("not unchecked", cause);
        }
    }
}
