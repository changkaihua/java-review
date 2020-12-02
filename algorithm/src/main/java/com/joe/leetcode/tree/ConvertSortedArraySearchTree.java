package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 *
 * @author ckh
 * @since 11/26/2020
 */
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
public class ConvertSortedArraySearchTree {


    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


}
