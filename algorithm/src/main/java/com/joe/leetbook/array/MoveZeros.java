package com.joe.leetbook.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ckh
 * @since 2020/12/17
 */
public class MoveZeros {
    /**
     * 这是后面补零, 不是移动 0
     */
    public void moveZeroesV1(int[] nums) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
        }
        System.out.println(left);
        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes(int[] nums) {
        int  left = 0, right = 0;
        for (; right < nums.length; right++) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    @Test
    public void test() {
        int[] arr = {0, 1, 0, 3, 12};
        moveZeroes(arr);

        System.out.println("arr = " + Arrays.toString(arr));
    }
}
