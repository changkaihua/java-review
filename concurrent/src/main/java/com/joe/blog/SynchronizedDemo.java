package com.joe.blog;

import java.util.Vector;

/**
 * [深入分析synchronized实现原理](http://cmsblogs.com/?p=2071)
 *
 * @author ckh
 * @since 2020/12/4
 */
public class SynchronizedDemo {


    /**
     * 锁消除:
     * 在有些情况下，JVM检测到不可能存在共享数据竞争，这时 JVM 会对这些同步锁进行锁消除。锁消除的依据是逃逸分析的数据支持
     * 我们虽然没有显示使用锁, 但是 Vector的 add()方法存在隐形的加锁操作
     * 在运行这段代码时，JVM可以明显检测到变量 vector 没有逃逸出方法 vectorTest() 之外
     * 所以JVM可以大胆地将vector内部的加锁操作消除
     * <p>
     * 锁粗化:
     * 就是将多个连续的加锁、解锁操作连接在一起，扩展成一个范围更大的锁
     * 对下面代码来说, vector每次add的时候都需要加锁操作，JVM检测到对同一个对象（vector）连续加锁、解锁操作，
     * 会合并一个更大范围的加锁、解锁操作，即加锁解锁操作会移到 for 循环之外
     */
    public void vectorTest() {
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < 10; i++) {
            vector.add(i + "");
        }
        System.out.println(vector);
    }

}
