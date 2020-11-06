package com.joe.leetbook.tree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ckh
 * @create 11/6/20 10:40 AM
 */
public class TreeRecursion {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode l = queue.poll();
                TreeNode r = queue.poll();
                if (l == null && r == null) continue;
                if ((l == null || r == null) || l.val != r.val) return false;

                queue.offer(l.left);
                queue.offer(r.right);

                queue.offer(l.right);
                queue.offer(r.left);
            }
        }
        return true;
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return check(root, root);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        int temp = sum - root.val;
        if (root.right == null && root.left == null && temp == 0) return true;
        return hasPathSum(root.left, temp) || hasPathSum(root.right, temp);
    }


    @Test
    public void test() {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        root.right = new TreeNode(2, new TreeNode(4), new TreeNode(3));

        System.out.println(isSymmetric2(root));
    }
}
