package com.joe.jvm.part3gc;

import com.joe.jvm.util.GetAddress;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * @author ckh
 * @create 10/13/20 10:56 AM
 */
public class TestAllocateMemory {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
//        testAllocation();
//        testPretenureSizeThreshold();
//        testTenuringThreshold();
        testTenuringThreshold2();
//        testHandlePromotion();
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        // Serial 会出现一次 MinorGC
        // Parallel Scavenge 会直接分配在老年代
        allocation4 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        System.out.println(1);
        byte[] allocation5 = new byte[4 * _1MB];
        System.out.println(2);
        System.out.println(3);
        byte[] allocation6 = new byte[2 * _1MB];

        System.out.println(4);

    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        // Serial 会直接分配在老年代
        // Parallel Scavenge 在 Eden 区足够的情况下，还是优先分配在Eden区，
        // 上面的方法表示，在Eden区不足的情况下，会直接分配老年代，而不会触发MinorGC
        allocation = new byte[4 * _1MB];
    }

    /**
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * <p>
     * 1.8 在上面的参数跑不出来 Serial 的 MaxTenuringThreshold=15 的效果，
     * 因为有动态年龄判断，除了自身分配的这3个对象外，还有一些其他的资源导致 同年对象达到Survivor空间的一半，直接晋升到老年代了
     * <p>
     * Parallel Scavenge  的 MaxTenuringThreshold 没看出来区别
     * 可以参考 这个博客： https://blog.csdn.net/zjysource/article/details/53749352
     * <p>
     * 将参数扩大一倍后可以测试出书上的效果
     * -Xms40M -Xmx40M -Xmn20M
     */
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;

        allocation1 = new byte[_1MB / 2];

        // 什么时候进入老年代取决于 XX：MaxTenuringThreshold 设置
        allocation2 = new byte[8 * _1MB];

        // gc
        allocation3 = new byte[8 * _1MB];

        allocation3 = null;
        // gc
        allocation3 = new byte[8 * _1MB];
//        allocation3 = new byte[8 * _1MB];

    }


    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 3];
        // allocation1 + allocation2 大于 survivor 空间一半, 提前晋升了
//        allocation2 = new byte[_1MB / 3];
        allocation3 = new byte[8 * _1MB];
        allocation4 = new byte[8 * _1MB];
        allocation4 = null;
        allocation4 = new byte[8 * _1MB];
    }

    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;

        System.out.println("begin minor gc");
        allocation4 = new byte[2 * _1MB];
        System.out.println("end minor gc");

        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;

        System.out.println("begin minor gc");
        allocation7 = new byte[2 * _1MB];
        System.out.println("end minor gc");
    }


}
