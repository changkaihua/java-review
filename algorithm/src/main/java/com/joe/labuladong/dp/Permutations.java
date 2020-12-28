package com.joe.labuladong.dp;

import com.joe.leetbook.tree.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. Permutations 全排列
 *
 * @author ckh
 * @since 2020/12/28
 */
public class Permutations {


    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return ans;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            // 这里直接 add track 添加的一直是同一个对象; 并且, 最后会 removeLast 导致size == 0;
            // 这里需要的是值
//            ans.add(track);
            ans.add(new LinkedList<>(track));
            return;
        }
        for (int num : nums) {
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }
    }


    public List<List<Integer>> permuteV2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        rank(nums, 0, nums.length, lists);
        return lists;

    }


    //全排列，排列的数组，index，从当前位置排列，
    public static void rank(int[] nums, int index, int len, List<List<Integer>> lists) {
        if (index == len - 1) {
            List<Integer> numList = new ArrayList<>();
            for (int num : nums) {
                numList.add(num);
            }
            lists.add(numList);
            return;
        }
        for (int i = index; i < len; i++) {
            exchange(nums, i, index);
            rank(nums, index + 1, len, lists);
            exchange(nums, i, index);
        }
    }

    //交换元素
    public static void exchange(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    @Test
    public void test() {
        System.out.println(permuteV2(new int[]{1, 2, 3}));
    }
}
