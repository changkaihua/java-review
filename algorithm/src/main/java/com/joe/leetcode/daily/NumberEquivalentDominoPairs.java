package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.HashMap;

/**
 * 1128. Number of Equivalent Domino Pairs
 *
 * @author ckh
 * @since 2021/1/26
 */
public class NumberEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        if (dominoes == null || dominoes.length == 0) return 0;
        int[] num = new int[100];
        int count = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            count += num[val];
            num[val]++;
        }
        return count;
    }

    @Test
    public void test() {

        int[][] dominoes = {
                {1, 2},
                {2, 1},
                {1, 1},
                {1, 2},
                {2, 2}
        };

        System.out.println(numEquivDominoPairs(dominoes));
    }
}
