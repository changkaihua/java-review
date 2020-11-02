package com.joe.concurrent.part5;

import com.joe.concurrent.utils.Computable;
import com.joe.concurrent.utils.LaunderThrowable;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Memoizer
 * <p/>
 * Final implementation of Memoizer
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer<A, V> implements Computable<A, V> {
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> c;

    public Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = () -> c.compute(arg);

                FutureTask<V> ft = new FutureTask<>(eval);
                // 通过原子方法实现: 若没有则添加
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                // 当某个计算被取消或失败, 清除缓存
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw LaunderThrowable.launderThrowable(e.getCause());
            }

        }
    }
}