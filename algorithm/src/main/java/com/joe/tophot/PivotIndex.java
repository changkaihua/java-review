package com.joe.tophot;

import java.util.Arrays;

/**
 * 寻找数组的中心索引
 *
 * @author ckh
 * @since 2021/1/28
 */
public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }
}
