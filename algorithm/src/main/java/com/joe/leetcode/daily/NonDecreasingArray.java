package com.joe.leetcode.daily;

import com.sun.deploy.security.TrustRecorder;
import org.junit.Test;

/**
 * 665. 非递减序列
 *
 * @author ckh
 * @since 2021/2/7
 */
public class NonDecreasingArray {

    public boolean checkPossibility(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right && nums[left] <= nums[left + 1]) {
            left++;
        }
        if (left == right) return true;
        while (right > 0 && nums[right - 1] <= nums[right]) right--;
        if (right - left + 1 > 2) return false;

        if (left == 0 || right == nums.length - 1) return true;

        return nums[right + 1] >= nums[left] || nums[left - 1] <= nums[right];

    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 3, 4, 2, 4, 2, 5, 6};
        System.out.println(checkPossibility(nums));
    }
}
