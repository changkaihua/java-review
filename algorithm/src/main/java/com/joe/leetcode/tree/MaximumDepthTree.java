package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

/**
 * 104. 树的最大深度
 *
 * @author ckh
 * @since 2020/11/24 7:46 PM
 */
public class MaximumDepthTree {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3, new TreeNode(5), null)));
        int depth = maxDepth(root);
        System.out.println("depth = " + depth);
    }
}
