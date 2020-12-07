package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;
import sun.reflect.generics.tree.Tree;

/**
 * 230. 二叉搜索树中第K小的元素
 *
 * @author ckh
 * @since 2020/12/7
 */
@SuppressWarnings("ALL")
public class KthSmallestBST {

    public int kthSmallest(TreeNode root, int k) {
        int rootLocation = getCount(root.left) + 1;
        if (rootLocation > k) {
            return kthSmallest(root.left, k);
        } else if (rootLocation < k) {
            return kthSmallest(root.right, k - rootLocation);
        } else {
            return root.val;
        }
    }

    private int getCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return getCount(node.left) + getCount(node.right) + 1;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(5,
                new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
                new TreeNode(6));
        int i = kthSmallest(root, 3);
        System.out.println(i);
    }
}
