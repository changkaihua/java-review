package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.time.temporal.Temporal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 *
 * @author ckh
 * @since 2020/12/8
 */
public class TreeZigzagLevelTraversal {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        boolean flag = true;
        while (!stack.isEmpty()) {
            int size = stack.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (flag) {
                    TreeNode curr = stack.pollFirst();
                    temp.add(curr.val);
                    if (curr.left != null) stack.offerLast(curr.left);
                    if (curr.right != null) stack.offerLast(curr.right);
                } else {
                    TreeNode curr = stack.pollLast();
                    temp.add(curr.val);
                    if (curr.right != null) stack.offerFirst(curr.right);
                    if (curr.left != null) stack.offerFirst(curr.left);
                }
            }
            flag = !flag;
            ans.add(temp);
        }
        return ans;
    }

    @Test
    public void test() {
//        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null),
                new TreeNode(3, null, new TreeNode(5)));
        System.out.println(zigzagLevelOrder(root));
    }
}
