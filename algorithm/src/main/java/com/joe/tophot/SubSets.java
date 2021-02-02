package com.joe.tophot;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 *
 * @author ckh
 * @since 2021/2/2
 */
public class SubSets {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(int[] nums, int idx, List<Integer> path) {
        if (path.size() > nums.length) return;
        System.out.println("path = " + path);
        ans.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            System.out.println("before: "+path);
            dfs(nums, i + 1, path);
            path.remove(path.size() - 1);
            System.out.println("after: "+path);
        }
    }

    @Test
    public void test(){
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }


}
