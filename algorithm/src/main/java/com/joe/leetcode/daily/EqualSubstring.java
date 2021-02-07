package com.joe.leetcode.daily;

/**
 * 1208. 尽可能使字符串相等
 *
 * @author ckh
 * @since 2021/2/5
 */
public class EqualSubstring {

    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int[] diff = new int[len];
        for (int i = 0; i < len; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int left = 0, right = 0;
        int sum = 0;
        while (right < len) {
            sum += diff[right];
            while (sum > maxCost) {
                sum -= diff[left];
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        return maxLength;


    }
}
