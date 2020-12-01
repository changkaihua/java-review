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
        return null;

    }
}