package com.joe.concurrent.part5;

import com.joe.concurrent.utils.Computable;
import com.joe.concurrent.utils.LaunderThrowable;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Memoizer2
 * <p/>
 * Replacing HashMap with ConcurrentHashMap
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer3<A, V> implements Computable<A, V> {
    /**
     * 将 结果定义为 Future
     */
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    /**
     * 基本解决了 Memoizer2 的问题, 若结果已经计算出来则立即返回, 若其他线程正在计算, 则等待计算结果
     * 但是仍然存在两个线程计算出相同值的漏洞, 只是概率远小于2
     * 因为 if 代码块是非原子性的, check then set
     */
    public V compute(final A arg) throws InterruptedException {
        Future<V> future = cache.get(arg);
        if (future == null) {
            Callable<V> eval = () -> c.compute(arg);
            FutureTask<V> ft = new FutureTask<>(eval);
            future = ft;
            cache.put(arg, ft);
            // 执行计算过程
            ft.run();
        }

        try {
            return future.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }

    }
}