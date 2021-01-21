package com.joe.tophot;

import org.junit.Test;

import java.util.Arrays;

/**
 * 75. Sort Colors
 *
 * @author ckh
 * @since 2021/1/18
 */
public class SortColors {
    public void sortColorsV1(int[] nums) {
        int left = 0, rihgt = nums.length - 1;

        while (left <= rihgt) {
            if (nums[left] == 0) {
                left++;
                continue;
            }
            if (nums[left] == 2) {
                swap(nums, left, rihgt);
                if (nums[left] == 1) rihgt--;
                continue;
            }
            left++;
        }
    }

    public void sortColorsV2(int[] nums) {
        int n = nums.length;
        int idx = 0, left = 0, right = n - 1;
        while (idx <= right) {
            while (nums[idx] == 2 && idx <= right) {
                swap(nums, idx, right--);
            }
            if (nums[idx] == 0) {
                swap(nums, idx, left++);
            }
            idx++;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    @Test
    public void test() {
        int[] nums = {2, 0, 1};
        sortColorsV1(nums);

        System.out.println(Arrays.toString(nums));
    }
}
