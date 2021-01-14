package com.joe.tophot;

import org.junit.Test;

/**
 * 33. Search in Rotated Sorted Array
 *
 * @author ckh
 * @since 2021/1/14
 */
@SuppressWarnings("ALL")
public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[left] <= nums[mid]) {
                // 左半部分正常升序, 用二分判断左边
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右边正常正序, 二分判断右边
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;


    }

    @Test
    public void test() {
        int search = search(new int[]{4,5,6,7,0,1,2}, 0);
        System.out.println(search);

    }
}
