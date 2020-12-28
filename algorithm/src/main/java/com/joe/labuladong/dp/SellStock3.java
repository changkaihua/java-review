package com.joe.labuladong.dp;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * 123. 买卖股票的最佳时机 III
 * 最多可以完成 两笔 交易
 *
 * @author ckh
 * @since 2020/12/28
 */
public class SellStock3 {
    /**
     * 正确, 但是超时, 看 sellStock4
     */
    public int maxProfitV0(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int[] end = Arrays.copyOfRange(prices, j + 1, prices.length);
                res = Math.max(res, prices[j] - prices[i] + maxProfitOnce(end));
            }
        }
        return res;
    }

    public int maxProfitOnce(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int res = 0;
        int currMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            currMin = Math.min(prices[i], currMin);
            res = Math.max(res, prices[i] - currMin);
        }
        return res;
    }


    /**
     * dp[i][k][2] : 第 i 天, 交易了 k 次, 0 没有, 1 持有
     */
    public int maxProfitV1(int[] prices) {
        int maxK = 2;
        int len = prices.length;
        int[][][] dp = new int[len][maxK + 1][2];

        for (int i = 0; i < len; i++) {
            // 因为该题 k == 2, 所以可以直接枚举出 4 中情况, 优化
            for (int k = maxK; k > 0; k--) {
                if (i - 1 == -1) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][maxK][0];
    }


    public int maxProfitV2(int[] prices) {
        /*
         第一位数字表示 k, 第二位数字表示是否持有
         k只在买入时增加, 表示操作了一次
         dp11: 之前至多有一次买入, 目前持有
         dp20: 之前至多有2次买入, 目前不持有
         */
        int dp10 = 0, dp11 = -prices[0], dp20 = 0, dp21 = -prices[0];
        for (int price : prices) {
            // 不持有  =>  昨天没有 / 昨天有, 今天卖了
            dp20 = Math.max(dp20, dp21 + price);
            dp10 = Math.max(dp10, dp11 + price);
            // 持有 => 昨天有 / 昨天没有, 今天买
            dp21 = Math.max(dp21, dp10 - price);
            dp11 = Math.max(dp11, -price);

        }
        return dp20;
    }

    @Test
    public void test() {
        int[] prices = {3, 2, 6, 5, 0, 3};
        System.out.println(maxProfitV1(prices));
    }


}
