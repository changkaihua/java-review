package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 424. 替换后的最长重复字符
 *
 * @author ckh
 * @since 2021/2/2
 */
public class CharacterReplacement {

    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, maxSame = 0;
        int[] map = new int[26];
        int len = s.length();

        while (right < len) {
            int idx = s.charAt(right) - 'A';
            map[idx]++;
            maxSame = Math.max(maxSame, map[idx]);
            if (right - left + 1 > maxSame + k) {
                map[s.charAt(left++) - 'A']--;
                left++;
            }
            right++;
        }

        return right - left;
    }

    @Test
    public void test() {
        System.out.println(characterReplacement("ABAA", 0));
    }
}
