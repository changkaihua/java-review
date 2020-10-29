package com.joe.concurrent.part3;

import org.junit.Test;

/**
 * 1. 线程A将事件源传入构造函数，并且执行了registerListener的代码
 * 2. 在线程A给listOfEvents初始化之前，线程B触发了事件源，由于线程A已经往事件源注册了监听器，因此会执行onEvent函数，也就是doSomething(e);
 * 3. 而此时listOfEvents还没被初始化，因此listOfEvents.add(e)报空指针异常
 *
 * @author ckh
 * @create 10/29/20 9:29 AM
 */
public class ThisEscapeTest {

    class EventTest1 implements ThisEscape.Event {
    }

    class EventTest2 implements ThisSafe.Event {
    }

    @Test
    public void doSomething1() {

        try {
            ThisEscape thisEscape = new ThisEscape(e -> e.onEvent(new EventTest1()));

            ThisSafe thisSafe = ThisSafe.getInstance(e -> e.onEvent(new EventTest2()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}