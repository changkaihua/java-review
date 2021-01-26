package com.joe.tophot;

import java.util.*;

/**
 * @author ckh
 * @since 2021/1/25
 */
public class CombineSum {
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> ans = new ArrayList<>();
//        List<Integer> combine = new ArrayList<>();
//        dfs(candidates, target, ans, combine, 0);
//        return ans;
//    }

//    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
//        if (idx == candidates.length) {
//            return;
//        }
//        if (target == 0) {
//            ans.add(new ArrayList<>(combine));
//            return;
//        }
//        // 直接跳过
//        dfs(candidates, target, ans, combine, idx + 1);
//        // 选择当前数
//        if (target - candidates[idx] >= 0) {
//            combine.add(candidates[idx]);
//            dfs(candidates, target - candidates[idx], ans, combine, idx);
//            combine.remove(combine.size() - 1);
//        }
//    }


    public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        LinkedList<Integer> path = new LinkedList<>();
        dfs(candidates, 0, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int target, LinkedList<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序
            // 如果 target - candidates[i] < 0 说明target减后面的值必然也小于0, 没必要再算了, 提前剪枝
            int nextTarget = target - candidates[i];
            if (nextTarget < 0) break;

            path.addLast(candidates[i]);
            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, nextTarget, path, res);
            path.removeLast();
        }
    }


}
