package com.joe.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author ckh
 * @create 9/16/20 9:52 AM
 */
public class SpiTest {

    public static void main(String[] args) {

        /*
            1. 创建ServiceLoader
            2. 为service赋值
            3. 为loader赋值
            4. 为acc赋值
            5. 清空providers缓存
            6. 为lookupIterator赋值，其实就是创建一个LazyIterator延迟迭代器
         */
        ServiceLoader<Fruit> s = ServiceLoader.load(Fruit.class);

        Iterator<Fruit> it = s.iterator();

        while (it.hasNext()) {
            System.out.println(it.next().showName());
        }
    }
}
