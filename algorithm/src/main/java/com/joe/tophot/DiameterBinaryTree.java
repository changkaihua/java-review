package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

/**
 * 543. diameter of binary tree
 *
 * @author ckh
 * @since 2020/12/5
 */
public class DiameterBinaryTree {

    int maxStep = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxStep;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        // 左子树深度 + 右子树深度
        maxStep = Math.max(maxStep, left + right);
        return Math.max(left, right) + 1;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));

        System.out.println(diameterOfBinaryTree(root));
    }
}
