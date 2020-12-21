package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ckh
 * @since 2020/12/18
 */
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        if (s.length() == 0) return t.charAt(0);

//        Map<Character,Integer> map = new HashMap<>();
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
//            map.put(c, map.getOrDefault(c, 0) + 1);
            map[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
//            Integer mapOrDefault = map.getOrDefault(c, -2);
//            if (mapOrDefault < 0) return c;
//            map.put(c, mapOrDefault - 1);
//            if (map.get(c) < 0) return c;
            int count = map[c - 'a'];
            if (count <= 0) return c;
            map[c - 'a'] = count - 1;
        }

        return t.charAt(t.length() - 1);
    }

    @Test
    public void test() {
        System.out.println(findTheDifference("abcd", "abbcd"));
    }
}
