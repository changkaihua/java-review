package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. Recover Binary Search Tree
 *
 * @author ckh
 * @since 2020/12/4
 */
public class RecoverBinarySearchTree {
    /**
     * O(n) space
     */
    public void recoverTreeV1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int x = -1, y = -1;
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i + 1) < list.get(i)) {
                y = list.get(i + 1);
                if (x == -1) {
                    x = list.get(i);
                } else {
                    break;
                }
            }
        }
        swapTwoNodeVal(root, 2, x, y);
    }

    private void swapTwoNodeVal(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            swapTwoNodeVal(root.right, count, x, y);
            swapTwoNodeVal(root.left, count, x, y);
        }
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
