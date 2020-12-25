package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.Arrays;

/**
 * 455. Assign Cookies
 *
 * @author ckh
 * @since 2020/12/25
 */
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i = 0, j = 0; i < g.length && j < s.length; i++, j++) {
            while (j < s.length && g[i] > s[j]) {
                j++;
            }
            if (j < s.length) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] g = {1, 2, 3};
        int[] s = {1, 2, 1};
        System.out.println(findContentChildren(g, s));
    }
}
