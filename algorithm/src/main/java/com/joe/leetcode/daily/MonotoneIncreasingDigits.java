package com.joe.leetcode.daily;

import org.junit.Test;

/**
 * 738. Monotone Increasing Digits
 *
 * @author ckh
 * @since 2020/12/15
 */
public class MonotoneIncreasingDigits {

    public int monotoneIncreasingDigits(int N) {
        int i = 1, ans = N;
        while (i <= ans / 10) {
            // 每次取后两位数字
            int n = ans / i % 100;
            i *= 10;
            // 高位 > 低位
            if (n / 10 > n % 10) {
                ans = ans / i * i - 1;
            }
        }
        return ans;
    }

    public int monotoneIncreasingDigitsV2(int N) {
        int bit = 111111111, ans = 0;
        for (int i = 9; i > 0; i--) {
            while (bit + ans > N) {
                bit /= 10;
            }
            ans += bit;
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(monotoneIncreasingDigitsV2(361));
    }
}
