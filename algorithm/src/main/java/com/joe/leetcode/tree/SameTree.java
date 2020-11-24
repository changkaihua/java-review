package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;

/**
 * 100. 相同的树
 *
 * @author ckh
 * @since 2020/11/24 5:36 PM
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
