package com.joe.concurrent.part3;

/**
 * @author ckh
 * @create 10/29/20 10:23 AM
 */
public class ThisSafe {

    public final int id;
    public final String name;
    private final EventListener listener;

    private ThisSafe() {
        id = 1;

        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
        // 相当于 listener = this::doSomething

        name = "zbc";
    }

    public static ThisSafe getInstance(EventSource source) {
        ThisSafe safe = new ThisSafe();
        source.registerListener(safe.listener);
        return safe;
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
