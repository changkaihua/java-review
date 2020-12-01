package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import com.joe.leetbook.tree.TreeTraverse;
import org.junit.Test;

/**
 * @author ckh
 * @since 11/26/2020
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }

        /* 前序遍历位置 */
        // root 节点需要交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 让左右子节点继续翻转它们的子节点
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    @Test
    public void test() {
        TreeTraverse treeTraverse = new TreeTraverse();
        // 4,2,7,1,3,6,9
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));

        System.out.println("before" + treeTraverse.preorderTraversal(root) + "\n");
        invertTree(root);
        System.out.println(treeTraverse.preorderTraversal(root));

    }
}
