package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/7
 */
public class MaxPathSum {

    int max = 0;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        int temp = root.val + left + right;
        max = Math.max(temp, max);

        return root.val + Math.max(left, right);
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(-10, new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        System.out.println(maxPathSum(root));

    }
}
