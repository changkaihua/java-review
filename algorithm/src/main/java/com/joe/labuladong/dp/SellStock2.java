package com.joe.labuladong.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 122. 买卖股票的最佳时机 II
 * 可以买卖多次
 *
 * @author ckh
 * @since 2020/12/28
 */
public class SellStock2 {


    public int maxProfitV0(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int[] endPrices = Arrays.copyOfRange(prices, j + 1, prices.length);
                res = Math.max(res, prices[j] - prices[i] + maxProfitV0(endPrices));
            }
        }
        return res;
    }

    /**
     * dp
     * 1. 定义状态
     * dp[i][0]  表示第 i 天 不持有 可获得的最大利润
     * dp[i][1]  表示第 i 天 持有 可获得的最大利润
     * 2. 转移状态
     * 对于今天不持有，可以从两个状态转移过来：1. 昨天也不持有；2. 昨天持有，今天卖出。两者取较大值。
     * 对于今天持有，可以从两个状态转移过来：1. 昨天也持有；2. 昨天不持有，今天买入。两者取较大值
     */
    public int maxProfitV1(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * 针对 v1 优化, 压缩状态空间
     */
    public int maxProfitV2(int[] prices) {
        int notHaveStock = 0;
        int haveStack = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int temp = notHaveStock;
            notHaveStock = Math.max(notHaveStock, haveStack + prices[i]);
            haveStack = Math.max(haveStack, temp - prices[i]);
        }
        return notHaveStock;
    }


    /**
     * 贪心.... ???
     */
    public int maxProfitV3(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max((prices[i] - prices[i - 1]), 0);
        }
        return res;
    }


    @Test
    public void test() {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfitV2(prices));

    }
}
