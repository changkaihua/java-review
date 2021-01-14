package com.joe.leetcode.daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1018. Binary Prefix Divisible By 5
 *
 * @author ckh
 * @since 2021/1/14
 */
public class BinaryPrefixDivisibleBy5 {
    /**
     * 存在溢出问题
     */
    public List<Boolean> prefixesDivBy5V1(int[] A) {
        LinkedList<Boolean> ans = new LinkedList<>();

        int cur = 0;
        for (int num : A) {
            // cur 不断累加, 最终会溢出, 导致错误
            cur = cur * 2 + num;
            ans.add(cur % 5 == 0);
        }
        return ans;
    }

    public List<Boolean> prefixesDivBy5V2(int[] A) {
        LinkedList<Boolean> ans = new LinkedList<>();

        int cur = 0;
        for (int num : A) {
            cur = (cur * 2 + num) % 5;
            ans.add(cur == 0);
        }
        return ans;
    }
}
