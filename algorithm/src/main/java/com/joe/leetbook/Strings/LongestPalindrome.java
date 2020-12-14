package com.joe.leetbook.Strings;

import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/14
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int deleta = 0; deleta < n; ++deleta) {
            for (int i = 0; i + deleta < n; ++i) {
                int j = i + deleta;

                /*
                  i, j  为 s[i,j] 的字符串
                 */
                if (deleta == 0) {
                    dp[i][j] = true;
                } else if (deleta == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && deleta + 1 > ans.length()) {
                    ans = s.substring(i, i + deleta + 1);
                }
            }
        }
        return ans;

    }

    public String longestPalindromeV2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            /*
                中心扩展, 可能是 aba 这种的, 也可能是 abba 这种的
             */
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > (end - start)) {
                /*
                    i 是第一个定位的 index
                    a b a   len = 3;      a b b a   len = 4;
                      i                     i
                 */
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }


    int[] range;

    public String longestPalindromeV3(String s) {
        int n = s.length(), center = 0;
        char[] chars = s.toCharArray();
        this.range = new int[2];
        while (center < n) {
            center = expand(chars, center);
            center++;
        }
        return s.substring(range[0], range[1] + 1);
    }

    private int expand(char[] chars, int center) {
        int l = center, r = center;
        while (r < chars.length - 1 && chars[r] == chars[r + 1]) {
            r++;
        }
        center = r;
        while (l > 0 && r < chars.length - 1 && chars[l - 1] == chars[r + 1]) {
            l--;
            r++;
        }
        if (r - l > range[1] - range[0]) {
            range[0] = l;
            range[1] = r;
        }
        return center;
    }

    @Test
    public void test() {
        String s = "abbabs";
        System.out.println(longestPalindromeV3(s));
    }
}
