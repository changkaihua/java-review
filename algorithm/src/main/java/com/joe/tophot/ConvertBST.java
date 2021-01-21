package com.joe.tophot;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

/**
 * @author ckh
 * @since 2021/1/21
 */
@SuppressWarnings("UnusedReturnValue")
public class ConvertBST {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private int dfs(TreeNode root, int parentVal) {
        if (root == null) return parentVal;
        root.val += dfs(root.right, parentVal);
        return dfs(root.left, root.val);
    }


    // 使用了全局变量
    int sum = 0;

    public TreeNode convertBSTv2(TreeNode root) {
        if (root != null) {
            convertBSTv2(root.right);
            sum += root.val;
            root.val = sum;
            convertBSTv2(root.left);
        }
        return root;
    }


    @Test
    public void test() {
        TreeNode root = new TreeNode(
                4,
                new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3))),
                new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8)))
        );

        TreeNode.print(root);
        System.out.println("\n======");

        convertBST(root);
//        convertBSTv2(root);
        TreeNode.print(root);

    }


}
