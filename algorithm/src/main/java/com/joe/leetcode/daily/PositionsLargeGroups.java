package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 830. Positions of Large Groups
 *
 * @author ckh
 * @since 2021/1/5
 */
public class PositionsLargeGroups {


    public List<List<Integer>> largeGroupPositions(String s) {
        if (s == null || s.length() < 3) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        int pre = 0, len = s.length();
        while (pre < len) {
            int count = 0;
            while (pre + count < len && s.charAt(pre) == s.charAt(pre + count)) count++;
            if (count > 2) res.add(Arrays.asList(pre, pre + count - 1));
            pre += count;
        }
        return res;

    }

    @Test
    public void test() {
        String s = "abbxxxxzzy";
        System.out.println(largeGroupPositions(s));
    }
}
