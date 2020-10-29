package com.joe.concurrent.part3;

import org.junit.Test;

import javax.swing.text.html.HTML;
import java.lang.management.ThreadInfo;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author ckh
 * @create 10/29/20 3:47 PM
 */
public class StuffIntoPublicTest {

    @Test
    public void test() {
        StuffIntoPublic stuff = new StuffIntoPublic();

        new Thread(stuff::initialize, "thread1").start();

        new Thread(stuff.holder::assertSanity, "thread2").start();

        /*
        The reason why this is possible is that Java has a weak memory model
            thread1:
            1. Alloc memory to pointer1 of holder
            2. Write 42 to pointer1 at offset 0
            3. Write pointer1 to someStaticVariable
            can be see as thread2:
            1. Alloc Memory to pointer1 of holder
            2. Write pointer1 to someStaticVariable
            3. Write 42 to pointer1 at offset 0
         */
    }

}