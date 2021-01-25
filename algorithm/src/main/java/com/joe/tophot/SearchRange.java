package com.joe.tophot;

import com.sun.corba.se.spi.ior.iiop.IIOPFactories;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ckh
 * @since 2021/1/25
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int[] ans = new int[2];

        ans[0] = leftSearch(nums, target);
        ans[1] = rightSearch(nums, target);

        return ans;
    }

    private int rightSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) return -1;
        return right;
    }

    private int leftSearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= nums.length || nums[left] != target) return -1;
        return left;
    }

    public int[] searchRangeV2(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length
                && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * 用 lower 变量 复用了二分查找代码
     *
     * @param nums   array
     * @param target target value
     * @param lower  if lower is true, find the left range, either or right range
     * @return target value's index in array, or -1
     */
    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] ints = searchRange(nums, 8);
        System.out.println("ints = " + Arrays.toString(ints));
    }

}
