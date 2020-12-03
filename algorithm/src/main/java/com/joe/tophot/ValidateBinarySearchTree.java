package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import com.joe.leetcode.part0.RobotScope;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. Validate Binary Search Tree
 *
 * @author ckh
 * @since 2020/12/3
 */
@SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    // =============approach 2 =============

    /* recursion -> iteration */

    LinkedList<TreeNode> stack = new LinkedList();
    LinkedList<Integer> uppers = new LinkedList(),
            lowers = new LinkedList();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBSTV2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) {
                continue;
            }
            val = root.val;
            if (lower != null && val <= lower) {
                return false;
            }
            if (upper != null && val >= upper) {
                return false;
            }
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    // ============== approach 3 ==================

    public boolean isValidBSTV3(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new ArrayDeque<>();
        long temp = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= temp) return false;
            temp = root.val;
            root = root.right;
        }
        return true;
    }
}
