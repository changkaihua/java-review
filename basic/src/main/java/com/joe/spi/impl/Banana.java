package com.joe.spi.impl;

import com.joe.spi.Fruit;

/**
 * @author ckh
 * @create 9/16/20 9:51 AM
 */
public class Banana implements Fruit {

    @Override
    public String showName() {
        return "banana";
    }
}
