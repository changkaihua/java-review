package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 *
 * @author ckh
 * @since 2020/12/7
 */
public class PathSum2 {

    List<List<Integer>> ans = new LinkedList<>();
    Deque<Integer> queue = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, sum);
        return ans;
    }

    private void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        queue.offerLast(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            ans.add(new LinkedList<>(queue));
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
        queue.pollLast();
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(5,
                new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
                new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1))));


        System.out.println(pathSum(root, 22));
    }
}
