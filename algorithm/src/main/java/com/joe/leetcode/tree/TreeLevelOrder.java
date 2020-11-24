package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 107. 二叉树的层次遍历 II
 *
 * @author ckh
 * @since 2020/11/24 8:00 PM
 */
public class TreeLevelOrder {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return null;
        LinkedList<List<Integer>> ans = new LinkedList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                temp.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            ans.addFirst(temp);
        }
        return ans;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        List<List<Integer>> lists = levelOrderBottom(root);

        System.out.println(lists);
    }
}
