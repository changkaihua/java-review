package com.joe.leetbook.tree;

/**
 * @author ckh
 * @create 11/6/20 2:45 PM
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }

    public static void print(TreeNode root) {
        if (root != null) {
            print(root.left);
            System.out.print(" " + root.val + " ");
            print(root.right);
        }

    }
}
