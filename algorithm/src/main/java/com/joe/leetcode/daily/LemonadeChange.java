package com.joe.leetcode.daily;

import org.junit.Test;

import java.io.RandomAccessFile;

/**
 * @author ckh
 * @since 2020/12/10
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int income = 0;
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                ten++;
                five--;
            } else {
                twenty++;
                five--;
                if (ten > 0) {
                    ten--;
                } else {
                    five -= 2;
                }

            }
            if (five < 0) {
                return false;
            }
        }
        return true;

    }

    @Test
    public void test() {
        int[] arr = {5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5};
        System.out.println(lemonadeChange(arr));
    }
}

