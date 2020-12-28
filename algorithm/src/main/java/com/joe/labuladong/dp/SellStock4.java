package com.joe.labuladong.dp;

/**
 * 188. 买卖股票的最佳时机 IV
 * 指定买卖次数
 *
 * @author ckh
 * @since 2020/12/28
 */
public class SellStock4 {
    public int maxProfitV0(int k, int[] prices) {
        int len = prices.length;
        if (k > len / 2) {
            // k > len/2 相当于可以任意次买卖, 复用之前的任意次买卖函数, 采用贪心
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                res += Math.max((prices[i] - prices[i - 1]), 0);
            }
            return res;
        }

        int[][][] dp = new int[len][k + 1][2];

        for (int i = 0; i < len; i++) {
            for (int j = k; j > 0; j--) {
                if (i - 1 == -1) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }

}
