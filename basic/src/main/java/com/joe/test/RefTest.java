package com.joe.test;

import java.lang.reflect.Method;

/**
 * java 反射慢在哪?
 *
 * @author ckh
 * @since 2020/12/28
 */
public class RefTest {
    public static void main(String[] args){
        try {
            Class<?> clz = Class.forName("com.joe.test.B");
            Object o = clz.newInstance();
            Method m = clz.getMethod("foo", String.class);
            for (int i = 0; i < 16; i++) {
                m.invoke(o, Integer.toString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
