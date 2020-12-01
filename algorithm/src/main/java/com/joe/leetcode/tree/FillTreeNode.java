package com.joe.leetcode.tree;

import com.joe.leetbook.tree.Node;

/**
 * 任何递归型的问题，无非就是灵魂三问：
 * 1、这个函数是干嘛的？
 * 2、这个函数参数中的变量是干什么的？
 * 3、得到函数的递归结果，你应该干什么？
 *
 * @author ckh
 * @since 12/1/2020
 */
public class FillTreeNode {
    public Node connect(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;

    }

    private void connectTwoNode(Node left, Node right) {
        if (left == null || right == null) return;
        left.next = right;

        connectTwoNode(left.left,left.right);
        connectTwoNode(right.left,right.right);
        connectTwoNode(left.right,right.left);

    }
}
