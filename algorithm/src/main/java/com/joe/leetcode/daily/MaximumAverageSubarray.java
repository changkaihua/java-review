package com.joe.leetcode.daily;

import org.junit.Test;

/**
 * 643. Maximum Average Subarray I
 *
 * @author ckh
 * @since 2021/2/4
 */
public class MaximumAverageSubarray {
    public double findMaxAverageV1(int[] nums, int k) {
        int left = 0, right = left + k - 1;
        double ans = -10_001, sum = 0.0;
        // 时间复杂度过高, 超时
        for (; left < nums.length - k + 1; left++) {
            sum = 0.0;
            for (int j = left; j <= right; j++) {
                sum += nums[j];
            }
            right++;
            ans = Math.max(ans, sum / k);

        }
        return ans;
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }

    public double find(int[] nums, int k) {
        int sum = 0, right = nums.length - k;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = 0; i < right; i++) {
            sum = sum - nums[i] + nums[k + i];
            maxSum = Math.max(sum, maxSum);
        }
        return 1.0 * maxSum / k;
    }

    @Test
    public void test() {
        int[] nums = {-3};
        double maxAverage = findMaxAverage(nums, 1);
        System.out.println(maxAverage);
    }
}
