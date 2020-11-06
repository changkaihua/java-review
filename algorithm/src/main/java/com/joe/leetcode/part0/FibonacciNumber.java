package com.joe.leetcode.part0;

import org.junit.Test;

/**
 * 509. Fibonacci Number
 * 体会动态规划的思想
 *
 * @author ckh
 * @create 10/27/20 8:20 PM
 */
@SuppressWarnings("AlibabaTestClassShouldEndWithTestNaming")
public class FibonacciNumber {

    /**
     * O(2^n)
     */
    int fib1(int n) {
        if (n == 1 || n == 2) return 1;
        return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * O(n)
     */
    int fib2(int n) {
        if (n < 1) return 0;
        int[] temp = new int[n + 1];
        return helper(temp, n);
    }

    int helper(int[] temp, int n) {
        if (n == 1 || n == 2) return 1;
        if (temp[n] != 0) return temp[n];
        temp[n] = helper(temp, n - 1) + helper(temp, n - 2);
        return temp[n];
    }


    @Test
    public void testFib2() {
        int i = fib2(45);
        System.out.println(i);
    }


    /**
     * o(n)
     */
    int fib3(int n) {
        if (n < 1) return 0;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 状态压缩
     */
    int fib4(int n) {
        if (n < 1) return 0;
        if (n == 2 || n == 1) return 1;
        int prev = 1, curr = 1;
        for (int i = 3; i <= n; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }


}
