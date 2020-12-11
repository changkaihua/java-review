package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;

/**
 * 538. Convert BST to Greater Tree
 *
 * @author ckh
 * @since 2020/12/10
 */
@SuppressWarnings("ALL")
public class ConvertBSTGreaterTree {

    int rightSum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            rightSum += root.val;
            root.val = rightSum;
            convertBST(root.left);
        }
        return root;
    }

}
