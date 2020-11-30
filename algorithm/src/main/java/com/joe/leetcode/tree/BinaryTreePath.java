package com.joe.leetcode.tree;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * @author ckh
 * @since 11/30/2020
 */
public class BinaryTreePath {
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null)
            return res;
        // 到达叶子节点，把路径加入到集合中
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }
        // 遍历左子节点的路径
        for (String path : binaryTreePaths2(root.left)) {
            res.add(root.val + "->" + path);
        }
        // 遍历右子节点的路径
        for (String path : binaryTreePaths2(root.right)) {
            res.add(root.val + "->" + path);
        }
        return res;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode root, String prefix, List<String> res) {
        if (root == null) return;
        // 使用 StringBuilder 优化 String 的拼接
        StringBuilder sb = new StringBuilder(prefix);
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            // 因为是 值拷贝, 所以不用撤销选择
            sb.append("->");
            dfs(root.left, sb.toString(), res);
            dfs(root.right, sb.toString(), res);
        }
    }


    private void dfs2(TreeNode root, String prefix, List<String> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(prefix + root.val);
            return;
        }
        // 因为是 值拷贝, 所以不用撤销选择
        dfs2(root.left, prefix + root.val + "->", res);
        dfs2(root.right, prefix + root.val + "->", res);
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, new TreeNode(6), new TreeNode(7, new TreeNode(8), null)));
        List<String> paths = binaryTreePaths(root);
        System.out.println(paths);
    }

}
