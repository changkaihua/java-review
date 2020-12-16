package com.joe.leetbook.array;

import org.junit.Test;

/**
 * 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * @author ckh
 * @since 2020/12/16
 */
public class FindMaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, slow = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 1) {
                max = Math.max(max, i - slow);
                slow = i + 1;
            }
        }
        max = Math.max(max, nums.length - slow);

        return max;
    }

    @Test
    public void test() {
        int[] arr = {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,0};
        System.out.println(findMaxConsecutiveOnes(arr));
    }
}
