package com.joe.tophot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15. 三数之和
 *
 * @author ckh
 * @since 2021/1/6
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) return lists;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int curr = nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int tmp = curr + nums[left] + nums[right];
                if (tmp == 0) {
                    lists.add(Arrays.asList(curr, nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left + 1] == nums[left]) left++;
                    while (left < right && nums[right - 1] == nums[right]) right--;
                    left++;
                    right--;
                } else if (tmp < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return lists;
    }
}
