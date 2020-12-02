package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;

/**
 * 654. 最大二叉树
 *
 * @author ckh
 * @since 12/1/2020
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        /*
            1. find max value
            2. left tree is constructMaximumBinaryTree(0, maxValueIndex-1)
            3. right tree is constructMaximumBinaryTree(maxValueIndex+1,nums.length-1)
         */
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {

        if (start > end) {
            return null;
        }

        int max = nums[start];
        int idx = start;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTree(nums, start, idx - 1);
        root.right = constructMaximumBinaryTree(nums, idx + 1, end);
        return root;
    }
}