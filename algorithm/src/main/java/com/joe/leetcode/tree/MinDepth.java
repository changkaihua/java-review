package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ckh
 * @since 11/26/2020
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left == null && curr.right == null) {
                    return depth + 1;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            depth++;
        }
        return depth;


    }
}
