package com.joe.leetcode.daily;

import org.junit.Test;

/**
 * 746. 使用最小花费爬楼梯
 *
 * @author ckh
 * @since 2020/12/21
 */
public class MinCostClimbingBuilding {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    /**
     * 压缩空间
     */
    public int minCostClimbingStairsV2(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = next;
        }
        return curr;
    }

    @Test
    public void test() {
        int[] arr = {0, 1, 2, 2};
        System.out.println(minCostClimbingStairs(arr));
    }
}
