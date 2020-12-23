package com.joe.leetbook.Strings;

import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/23
 */
public class FirstUniqueString {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }
}
