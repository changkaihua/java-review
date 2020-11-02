package com.joe.concurrent.utils;

/**
 * @author ckh
 * @create 11/2/20 11:19 AM
 */
@FunctionalInterface
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
