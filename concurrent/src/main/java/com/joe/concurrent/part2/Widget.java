package com.joe.concurrent.part2;

/**
 * @author ckh
 * @create 10/28/20 8:23 PM
 */
class Widget {
    public synchronized void doSomething() {
        System.out.println("doSomething...");
    }
}

class LoggingWidget extends Widget {
    @Override
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
    }
}
