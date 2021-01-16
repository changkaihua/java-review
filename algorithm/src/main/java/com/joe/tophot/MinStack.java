package com.joe.tophot;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ckh
 * @since 2021/1/15
 */
public class MinStack {
    int min = Integer.MAX_VALUE;
    int size = 0;
    int[] stack = new int[10000];

    public MinStack() {

    }

    public void push(int data) {
        if (min > data) min = data;
        stack[size++] = data;
    }

    public void pop() {
        if (stack[--size] == min) {
            // 数据量太小, 用这个得不偿失
//            min = Arrays.stream(stack).limit(size).min().orElse(Integer.MAX_VALUE);
            min = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                if (min > stack[i]) {
                    min = stack[i];
                }
            }
        }

    }

    public int top() {
        return stack[size - 1];
    }

    public int getMin() {
        return min;
    }
}
