package com.joe.test;

import java.util.Comparator;

/**
 * @author ckh
 * @since 2020/12/17
 */
public class Father {
    public static Father father = new Father();
    public int j = 0;

    static {
        System.out.println("Father static block");
    }

    {
        System.out.println("Father not static block");
    }

    public Father() {
        System.out.println("Father Constructor");
        Comparator<Integer> comparator = (a, b) -> "Raoul".length();
    }

}
