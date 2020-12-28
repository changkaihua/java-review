package com.joe.labuladong.dp;

import org.junit.Test;

/**
 * 121. 买卖股票的最佳时机
 * <p>
 * 只能买卖一次
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


    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfitV0(prices) == maxProfitV1(prices));
    }
}
