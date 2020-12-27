package com.joe.leetcode.daily;

import org.junit.Test;

/**
 * 205. Isomorphic Strings
 *
 * @author ckh
 * @since 2020/12/27
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int[] sTot = new int[26];
        int[] tTos = new int[26];
        for (int i = 0; i < ss.length; i++) {
            if (sTot[ss[i]-'a'] != tTos[tt[i]-'a']) {
                return false;
            }
            sTot[ss[i]-'a'] = i + 1;
            tTos[tt[i]-'a'] = i + 1;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(isIsomorphic("abaaadd", "babbbcd"));

    }
}
