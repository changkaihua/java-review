package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 * @author ckh
 * @since 12/1/2020
 */
public class InorderTraversalTree {


    /**
     * 迭代实现 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
       // 用栈模拟递归栈
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }

    private void inorder(TreeNode root, List<Integer> ans) {
        if (root != null) {
            inorder(root.left, ans);
            ans.add(root.val);
            inorder(root.right, ans);
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9)));

        List<Integer> list = inorderTraversal(root);
        System.out.println(list);

        System.out.println();
        System.out.println(inorderTraversal2(root));
    }
}
