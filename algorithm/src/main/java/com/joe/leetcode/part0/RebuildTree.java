package com.joe.leetcode.part0;

import java.util.HashMap;

/**
 * @author ckh
 * @create 11/2/20 5:14 PM
 */
public class RebuildTree {
    HashMap<Integer, Integer> infixDirectory = new HashMap<>();
    int[] temp;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        temp = preorder;
        for (int i = 0; i < inorder.length; i++)
            infixDirectory.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }

    /**
     * @param pre_root 前序遍历根节点
     * @param in_left  中序左节点
     * @param in_right 中序右节点
     * @return 根节点
     */
    TreeNode recur(int pre_root, int in_left, int in_right) {

        if (in_left > in_right) return null;

        TreeNode root = new TreeNode(temp[pre_root]);

        int indexOfRootInInorder = infixDirectory.get(temp[pre_root]);

        root.left = recur(pre_root + 1,
                in_left,
                indexOfRootInInorder - 1);

        // indexOfRootInInorder - in_left   左子树的长度
        root.right = recur(pre_root + indexOfRootInInorder - in_left + 1,
                indexOfRootInInorder + 1,
                in_right);

        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
