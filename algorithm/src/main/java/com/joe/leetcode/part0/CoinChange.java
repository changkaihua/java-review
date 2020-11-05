package com.joe.leetcode.part0;

import java.util.Arrays;

/**
 * 322. Coin Change
 *
 * @author ckh
 * @create 10/27/20 8:35 PM
 */
public class CoinChange {

    /**
     * O(mn)
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        // 初始条件
        dp[0] = 0;

        // 遍历所有状态的所有取值
        for (int i = 1; i <= amount; i++) {
            // 遍历所有选择, 求最小值
            for (int coin : coins) {
                if ((i - coin) < 0) continue;
                // 1 + dp[i - coin], i-coin 面值的钱 + coin面值 , 就是 i 面值
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


    /**
     * 暴力递归, 不好, 用记忆化优化, 见 method3
     */
    public int coinMethod2(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int ans = amount + 1;

        for (int coin : coins) {
            int subAns = coinMethod2(coins, amount - coin);
            if (subAns < 0) continue;
            ans = Math.min(ans, subAns + 1);
        }
        return ans == amount + 1 ? -1 : ans;
    }

    /**
     * 虽然保存了一下每个选择, 但是还是超时了
     */
    public int coinMethod3(int[] coins, int amount) {
        int[] buf = new int[amount + 1];
        Arrays.fill(buf, Integer.MAX_VALUE);
        return helper(coins, buf, amount);
    }

    private int helper(int[] coins, int[] buf, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        // 缓存命中, 直接返回, 不用再次计算
        if (buf[amount] != Integer.MAX_VALUE) return buf[amount];

        for (int coin : coins) {
            int subAns = helper(coins, buf, amount - coin);
            if (subAns < 0) continue;
            buf[amount] = Math.min(buf[amount], subAns + 1);
        }
        return buf[amount] == Integer.MAX_VALUE ? -1 : buf[amount];
    }


}
