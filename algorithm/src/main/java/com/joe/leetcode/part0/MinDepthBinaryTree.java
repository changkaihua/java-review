package com.joe.leetcode.part0;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. Minimum Depth of Binary Tree
 *
 * @author ckh
 * @create 11/1/20 8:39 PM
 */
public class MinDepthBinaryTree {


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minDepth(TreeNode root) {

        if (root == null) return 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            /*
                将当前队列中的所有节点向四周扩散
                通过开始的 size 保证, 下面的 for 循环 poll出来的都是同一层的节点
             */
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                /*
                    判断是否到达终点
                 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /*
                    将 cur 的相邻节点加入队列
                 */
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }

            /* 这里增加步数 */
            depth++;
        }

        return depth;

    }
}