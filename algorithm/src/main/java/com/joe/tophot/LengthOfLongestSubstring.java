package com.joe.tophot;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 3. 无重复字符的最长子串
 *
 * @author ckh
 * @since 2020/12/30
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 0, left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
//                left = map.get(c) + 1;
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    @Test
    public void test() {
        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
