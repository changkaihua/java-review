package com.joe.leetbook.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 209. Minimum Size Subarray Sum
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ckh
 * @since 2020/12/16
 */
public class MinimumSizeSubarraySum {
    public int minSubArrayLenV1(int s, int[] nums) {
        int left = 0, right = 0, sum = 0;
        int ans = Integer.MAX_VALUE;
        for (; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 利用前缀和实现
     * 每次相当于把第一个数划掉了, 所以长度为 bound - (i-1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // 前缀和数组
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            // 每次的目标值是 前 i 个数的和 + s
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    @Test
    public void test() {
        int[] arr = {2, 3, 1, 2, 4, 3, 1, 1, 5};
        System.out.println(minSubArrayLen(7, arr));
    }
}
