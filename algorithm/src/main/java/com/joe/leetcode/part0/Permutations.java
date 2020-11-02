package com.joe.leetcode.part0;

import java.util.LinkedList;
import java.util.List;

/**
 * 46. Permutations
 * 全排列
 *
 * @author ckh
 * @create 10/28/20 9:58 PM
 */
public class Permutations {
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {

        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();

        backtrack(nums, track);

        return ans;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num)) continue;
            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}
