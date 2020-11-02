package com.joe.concurrent.part5;

import com.joe.concurrent.utils.Computable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memoizer2
 * <p/>
 * Replacing HashMap with ConcurrentHashMap
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer2<A, V> implements Computable<A, V> {
    /**
     * concurrentHashMap 是线程安全的, 所以在访问Map时不用进行同步
     */
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 多线程可以并发的使用, 但是有一个漏洞
     * 如果某个线程A启动了一个开销很大的计算, 线程B不知道A 正在计算, 可能会重复计算, 造成浪费
     */
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
