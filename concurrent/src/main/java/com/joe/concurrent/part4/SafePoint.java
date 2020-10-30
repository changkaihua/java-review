package com.joe.concurrent.part4;

import com.joe.annotations.GuardedBy;
import com.joe.annotations.ThreadSafe;

/**
 * SafePoint
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafePoint {
    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        // 如果这里改为 this(p.x, p.y) 会产生竟态条件 这样写的前提是 x,y 是public 的, 所以会有竟态条件
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.set(x, y);
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}