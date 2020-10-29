package com.joe.concurrent.part3;

import java.util.concurrent.TimeUnit;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 * <p>
 * 1. 线程A将事件源传入构造函数，并且执行了registerListener的代码
 * 2. 在线程A给listOfEvents初始化之前，线程B触发了事件源，由于线程A已经往事件源注册了监听器，因此会执行onEvent函数，也就是doSomething(e);
 * 3. 而此时listOfEvents还没被初始化，因此listOfEvents.add(e)报空指针异常
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThisEscape {

    public final int id;
    public final String name;


    /**
     * 公有的构造器, 在对象没有构造成功时, 就发布了 this 对象的引用
     * 不好理解, 参考:
     * https://hzy38324.gitbooks.io/java-concurrency-in-practice/content/how-to-write-un-thread-safe-code.html
     * https://blog.csdn.net/flysqrlboy/article/details/10607295
     */
    public ThisEscape(EventSource source) {

        id = 1;
        /*
         * 自己理解:
         * registerListener(EventListener e) 方法的参数 e 是可以被别的线程或类修改的, 也就是说, e 是被发布的
         * 换句更形象的, 如果在 registerListener() 中保存了 EventListener 对象, 那么 e 就被发布了;
         * 而 EventListener 是 ThisEscape 的内部类, 内部类自动持有外部类的引用, 所以 this 也被发布了, 而此时构造函数并未执行完
         * 有风险, 如果调用 ThisEscape 的方法, 则可能出错, 因为可能用到了 this
         */
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                // 调用的是 ThisEscape 的方法
                doSomething(e);
            }
        });
        // 相当于: source.registerListener(this::doSomething)


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        name = "zbc";
    }

    void doSomething(Event e) {
        System.out.println(e);
        System.out.println("id = " + this.id + "; name = " + this.name);
    }


    @FunctionalInterface
    interface EventSource {
        void registerListener(EventListener e);
    }

    @FunctionalInterface
    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}
