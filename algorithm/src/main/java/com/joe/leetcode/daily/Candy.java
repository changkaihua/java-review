package com.joe.leetcode.daily;

import org.junit.Test;

import javax.crypto.Cipher;
import java.util.Arrays;

/**
 * @author ckh
 * @since 2020/12/24
 */
public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    @Test
    public void test() {
        System.out.println(candy(new int[]{1, 0, 2}));
    }
}
