package com.joe.spi.impl;

import com.joe.spi.Fruit;

/**
 * @author ckh
 * @create 9/16/20 9:50 AM
 */
public class Apple implements Fruit {

    @Override
    public String showName() {
        return "hello apple";
    }
}
