package com.joe.tophot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 *
 * @author ckh
 * @since 2021/1/29
 */
public class FindDisappearedNumbers {
    public List<Integer> findDisappearedNumbersV1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int[] map = new int[nums.length + 1];
        map[0] = -1;
        for (int num : nums) {
            map[num]++;
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0) ans.add(i);
        }
        return ans;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        // 1 <= nums[i] <= nums.length
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] > 0) nums[idx] *= -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) ans.add(i+1);
        }
        return ans;
    }

    @Test
    public void test(){
        int[] nums = {2,4,5,1,1};
        System.out.println(findDisappearedNumbers(nums));
    }
}
