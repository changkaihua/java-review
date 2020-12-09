package com.joe.leetcode.part0;

import com.sun.org.apache.bcel.internal.generic.I2B;
import org.junit.Test;

import java.util.StringTokenizer;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class DemoTest {

    @Test
    public void test() {
        StringTokenizer st = new StringTokenizer("Hello World");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

    @Test
    public void testInteger() {
        int i = 128;
        Integer i1 = 128;
        Integer i2 = new Integer(128);
        // integer 自动拆箱 , true
        System.out.println(i == i1);
        // false
        System.out.println(i1 == i2);

        Integer i3 = 127;
        Integer i4 = new Integer(127);
        Integer i5 = Integer.valueOf(127);
        int i6 = 127;

        // false
        System.out.println(i3 == i4);
        // true
        System.out.println(i3 == i5);
        // false
        System.out.println(i4 == i5);
        // all true
        System.out.println(i6 == i3);
        System.out.println(i6 == i4);
        System.out.println(i6 == i5);


    }
}
