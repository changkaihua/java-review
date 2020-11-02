package com.joe.concurrent.part5;

import com.joe.annotations.GuardedBy;
import com.joe.concurrent.utils.Computable;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Memoizer1
 * <p>
 * Initial cache attempt using HashMap and synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer1<A, V> implements Computable<A, V> {
    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    /**
     * 指明缓存类型
     */
    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * hashMap 不是线程安全的, 采用保守的方法, 对整个方法进行同步, 能保证线程安全
     * 但是会带来明显的可伸缩问题, 每次只有一个线程能够执行 compute, 可能阻塞
     * 虽然线程安全了, 但是并发性很差
     */
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

class ExpensiveFunction implements Computable<String, BigInteger> {
    public BigInteger compute(String arg) {
        // after deep thought... mock compute time
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BigInteger(arg);
    }
}
