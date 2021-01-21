package com.joe.tophot;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 169 多数元素
 *
 * @author ckh
 * @since 2021/1/21
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long limit = nums.length >> 1;
        for (Map.Entry<Integer, Long> entry : map.entrySet())
            if (entry.getValue() > limit) return entry.getKey();
        return -1;
    }

    public int majorityElementV2(int[] nums) {
        int vote = nums[0], count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (vote == nums[i]) {
                count++;
            } else if (count-- == 0) {
                vote = nums[i];
                count = 1;
            }
        }
        return vote;
    }

}
