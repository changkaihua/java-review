package com.joe.leetbook.tree;

import java.util.*;

/**
 * @author ckh
 * @create 11/5/20 7:53 PM
 */
public class TreeTraverse {

    /**
     * 使用迭代实现
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        /* 这就不是后序遍历, 只是最后 res 打印的顺序与后序遍历相同*/
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        Deque<Integer> temp = new ArrayDeque<>();
        for (Integer re : res) {
            temp.addFirst(re);
        }
        res = new ArrayList<>(temp);
        return res;
    }

    private void preorder(List<Integer> res, TreeNode root) {
        if (root != null) {
            res.add(root.val);
            inorder(res, root.left);
            inorder(res, root.right);
        }
    }

    private void inorder(List<Integer> res, TreeNode root) {
        if (root != null) {
            inorder(res, root.left);
            res.add(root.val);
            inorder(res, root.right);
        }
    }

    private void postOrder(List<Integer> res, TreeNode root) {
        if (root != null) {
            postOrder(res, root.left);
            postOrder(res, root.right);
            res.add(root.val);
        }
    }


    /**
     * 层序遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        // ArrayDeque 不能存放 null 值
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                temp.add(curr.val);
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            res.add(temp);
        }

        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2, new TreeNode(3), null);

        TreeTraverse treeTraverse = new TreeTraverse();
        System.out.println(treeTraverse.inorderTraversal(root));
//        System.out.println(list);
    }
}
