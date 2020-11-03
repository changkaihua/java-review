package com.joe.leetcode.part0;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * @author ckh
 * @create 11/3/20 8:53 PM
 */
public class FindPositionSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int left = left_bound(nums, target);
        int right = right_bound(nums, target);
        return new int[]{left, right};
    }

    int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                // 缩减区间
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    int right_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 退出条件: left = right + 1 [left, right]
        while (left <= right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                // 收缩边界
                left = mid + 1;
            } else if (nums[mid] > target) {
                // [left, mid - 1]
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) return -1;
        return right;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 3, 3, 3};
        System.out.println(new FindPositionSortedArray().left_bound(nums, 3));
        System.out.println(new FindPositionSortedArray().right_bound(nums, 3));

    }
}
