package com.joe.leetcode.daily;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 290. Word Pattern
 *
 * @author ckh
 * @since 2020/12/16
 */
public class WordPattern {
    /**
     * 只解决了单方面的映射, 该题需要的是双射
     */
    public boolean wordPatternError(String pattern, String s) {
        String[] strings = s.split(" ");
        if (pattern.length() != strings.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>(strings.length);
        char[] array = pattern.toCharArray();
        for (int i = 0; i < array.length; i++) {
            String str = map.getOrDefault(pattern.charAt(i), "");
            if ("".equals(str)) {
                map.put(array[i], strings[i]);
            } else {
                if (!strings[i].equals(str)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<>();
        Map<Character, String> ch2str = new HashMap<>();
        int strLen = str.length();
        int start = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (start >= strLen) {
                return false;
            }
            int end = start;
            while (end < strLen && str.charAt(end) != ' ') {
                end++;
            }
            String tmp = str.substring(start, end);

            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            start = end + 1;
        }
        return start >= strLen;

    }

    @Test
    public void test() {
        String pattern = "abba";
        String str = "dog cat cat dog";
        System.out.println(wordPattern(pattern, str));

    }
}
