package com.joe.labuladong.dp;

import org.junit.Test;

/**
 * 121. 买卖股票的最佳时机
 * 只买卖一次
 *
 * @author ckh
 * @since 2020/12/28
 */
public class SellStock1 {

    /**
     * 暴力, 固定买入价格, 遍历所有的卖出, 找到最大值
     */
    public int maxProfitV0(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                res = Math.max(res, prices[j] - prices[i]);
            }
        }
        return res;
    }

    /**
     * 减少一层循环, 因为只需要找到当前之前的最小的买入时间
     */
    public int maxProfitV1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int res = 0;
        int currMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            currMin = Math.min(prices[i], currMin);
            res = Math.max(res, prices[i] - currMin);
        }
        return res;
    }


    public int maxProfitV2(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * dp
     */
    public int maxProfitV3(int[] prices) {
        int notStock = 0;
        int haveStock = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            notStock = Math.max(notStock, haveStock + prices[i]);
            haveStock = Math.max(haveStock, -prices[i]);
        }
        return notStock;
    }


    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfitV2(prices));
        System.out.println(maxProfitV3(prices));
    }
}
