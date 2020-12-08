package com.joe.leetbook.array;

import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/8
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        // 1,3,5,6   0
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return left;
    }

    @Test
    public void test(){
        int[] arr = {1,3,4,6,7};
        System.out.println(searchInsert(arr, 7));
    }
}
