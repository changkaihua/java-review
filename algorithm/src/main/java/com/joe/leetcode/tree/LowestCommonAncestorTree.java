package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * @author ckh
 * @since 11/27/2020
 */
public class LowestCommonAncestorTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = root;
        while (true) {
            if (p.val < ans.val && q.val < ans.val) {
                ans = ans.left;
            } else if (p.val > ans.val && q.val > ans.val) {
                ans = ans.right;
            } else {
                break;
            }
        }
        return ans;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, new TreeNode(6), new TreeNode(7, new TreeNode(8), null)));
        TreeNode treeNode = lowestCommonAncestor(root, new TreeNode(5),new TreeNode(8));
        System.out.println(treeNode);
    }
}
