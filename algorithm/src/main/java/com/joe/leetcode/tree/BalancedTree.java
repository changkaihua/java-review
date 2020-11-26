package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;

/**
 * 110. 平衡二叉树
 *
 * @author ckh
 * @since 11/26/2020
 */
public class BalancedTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.abs(leftDepth - rightDepth) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);

    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }


}
