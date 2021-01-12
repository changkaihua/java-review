package com.joe.tophot;

import org.junit.Test;

import java.util.Arrays;

/**
 * 53. 最大子序和
 *
 * @author ckh
 * @since 2021/1/12
 */
public class MaxSubArray {
    /**
     * dp
     */
    public int maxSubArrayV1(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxAns = Math.max(pre, maxAns);
        }
        return maxAns;
    }

    public int maxSubArrayV2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    @Test
    public void test() {
        int i = maxSubArrayV1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println("i = " + i);
    }
}
