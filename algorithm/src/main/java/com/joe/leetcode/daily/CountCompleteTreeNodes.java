package com.joe.leetcode.daily;

import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ckh
 * @create 11/24/20 5:21 PM
 */
public class CountCompleteTreeNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                ans++;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
        return ans;
    }


    public int countNodesMethod2(TreeNode root) {
        if (root == null) return 0;
        return countNodesMethod2(root.left) + countNodesMethod2(root.right) + 1;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), null));

        int countNodes = countNodes(root);

        System.out.println("countNodes = " + countNodes);
    }
}
