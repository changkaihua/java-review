package com.joe.tophot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ckh
 * @since 2020/12/3
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx1 = map.getOrDefault(nums[i], -1);
            if (idx1 == -1) {
                map.put(target - nums[i], i);
            } else {
                return new int[]{idx1, i};
            }
        }
        return null;
    }

}
