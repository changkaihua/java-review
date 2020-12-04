package com.joe.blog;

import javax.swing.text.html.HTML;

/**
 * @author ckh
 * @since 2020/12/4
 */
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREAD = ThreadLocal.withInitial(System::currentTimeMillis);

    public static void begin() {
//        TIME_THREAD.set(System.currentTimeMillis());
        // 没有设置值时, 会把初始化的值设置到 threadLocal 中,
        // 结合初始化时的值, 等效上面那句
        TIME_THREAD.get();
    }

    public static long end() {
        return System.currentTimeMillis() - TIME_THREAD.get();
    }

    public static void main(String[] args) {
        Profiler.begin();
        SleepUtils.second(1);
        System.out.println("cost: " + Profiler.end() + "ms");
    }
}
