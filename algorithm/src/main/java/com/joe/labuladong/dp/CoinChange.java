package com.joe.labuladong.dp;

import org.junit.Test;

import java.util.Arrays;

/**
 * 322. Coin Change
 *
 * @author ckh
 * @since 2020/12/25
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    @Test
    public void test() {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }
}
