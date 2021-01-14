package com.joe.tophot;

import org.junit.Test;

/**
 * 70. Climbing stairs
 *
 * @author ckh
 * @since 2021/1/14
 */
public class ClimbingStairs {

    public int climbStairsV1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 状态压缩
     */
    public int climbStairsV2(int n) {
        int ans = 1, pre = 0, cur = 0;
        for (int i = 0; i < n; i++) {
            pre = cur;
            cur = ans;
            ans = pre + cur;
        }
        return ans;
    }

    /**
     * 问题变种: 在前文的基础上增加一个限制条件, 不能连续跳 2 个台阶, 也就是说有一次跳过2个台阶后, 下一次只能跳 1 步
     * <p>
     * 假设：
     * f(x) 表示爬到第 x 级台阶的方案数，
     * g(x, 1) 表示爬到第 x 级台阶并且最后一步只跨越一个台阶的方案数，
     * g(x, 2) 表示爬到第 x 级台阶并且最后一步跨越了两个台阶的方案数。
     * <p>
     * 由 ：
     * f(x) = g(x, 1)+g(x,2)，
     * g(x, 1) = f(x-1)，
     * g(x, 2) = g(x-2, 1) // 最后一步跨越了两步，那么上一步只能跨越一步
     * <p>
     * 得：
     * f(x) = g(x, 1) + g(x, 2)
     * = f(x-1) + g(x-2, 1)
     * = f(x-1) + f((x-2)-1)
     * = f(x-1) + f(x-3)
     */
    public int climbingStairsV3(int n) {
        // 防止 dp[2] 溢出
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 3];
        }
        return dp[n];
    }

    @Test
    public void test() {
        int n = 3;
        int stairsV1 = climbStairsV1(n);
        int stairsV2 = climbStairsV2(n);
        System.out.println(stairsV1);
        System.out.println(stairsV2);
    }
}
