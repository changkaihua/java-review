package com.joe.jvm.part8;

/**
 * 静态分派演示
 *
 * @author ckh
 * @create 10/19/20 4:02 PM
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {
    }

    static class WoMan extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello guy");

    }

    public void sayHello(Man guy) {
        System.out.println("hello gentleman");
    }

    public void sayHello(WoMan guy) {
        System.out.println("hello lady");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new WoMan();

        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
