package com.joe.leetbook.array;

import org.junit.Test;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cwuyj/
 *
 * @author ckh
 * @since 2020/12/16
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        for (; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    @Test
    public void test() {
        int[] arr = {3, 2, 2, 3};
        System.out.println(removeElement(arr, 3));
    }
}
