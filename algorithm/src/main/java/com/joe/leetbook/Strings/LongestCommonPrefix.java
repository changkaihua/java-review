package com.joe.leetbook.Strings;

import java.util.Map;

/**
 * @author ckh
 * @since 2020/12/14
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longCommonPre(prefix, strs[i]);
            if (prefix.length() == 0) break;
        }
        return prefix;
    }

    private String longCommonPre(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
}
