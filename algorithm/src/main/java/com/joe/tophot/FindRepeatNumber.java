package com.joe.tophot;

/**
 * @author ckh
 * @since 2020/12/3
 */
public class FindRepeatNumber {

    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int idx = nums[i];
                nums[i] = nums[idx];
                nums[idx] = idx;
            }
        }
        return -1;
    }
}
