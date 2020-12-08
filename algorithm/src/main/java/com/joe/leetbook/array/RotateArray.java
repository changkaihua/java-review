package com.joe.leetbook.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        int[] temp = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            temp[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }

    public void rotateV2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotateV3(int[] nums, int k) {
        k = k % nums.length;
        //
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
                // 当再次 current = start 注意是再次，说明已经换过一轮了
                // 再按当前 start 去置换只会重复之前的换位，所以结束这一轮换位开始下一轮换位
            } while (start != current);
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }
}
