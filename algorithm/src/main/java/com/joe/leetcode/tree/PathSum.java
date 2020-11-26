package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import sun.security.provider.Sun;

/**
 * @author ckh
 * @since 11/26/2020
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        int sub = sum - root.val;
        if (root.left == null && root.right == null && sub == 0) return true;
        return hasPathSum(root.left, sub) || hasPathSum(root.right, sub);

    }
}
