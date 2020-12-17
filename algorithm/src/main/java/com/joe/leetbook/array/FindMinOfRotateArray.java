package com.joe.leetbook.array;

/**
 * @author ckh
 * @since 2020/12/17
 */
public class FindMinOfRotateArray {

    public int findMin(int[] nums) {
        int min = nums[0], left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] < nums[left]) {
                min = nums[right];
                break;
            }
        }
        return min;
    }
}
