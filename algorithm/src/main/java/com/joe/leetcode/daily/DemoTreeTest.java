package com.joe.leetcode.daily;

import com.joe.leetbook.tree.Node;
import com.joe.leetbook.tree.TreeNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * this is for practice tree
 *
 * @author ckh
 * @since 2020/12/3
 */
public class DemoTreeTest {

    /**
     * flatten the tree
     * 1. flatten the left tree
     * 2. flatten the right tree
     * 3. root.left = null ->  root.right = root.left -> root.left= root.right
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    private void connectTwoNode(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }
        left.next = right;
        connectTwoNode(left.left, left.right);
        connectTwoNode(left.right, right.left);
        connectTwoNode(right.left, right.right);
    }

    /**
     * just perfect tree
     */
    public Node connectV2(Node root) {
        if (root == null) {
            return null;
        }
        Node leftMost = root;

        while (leftMost.left != null) {

            Node curr = leftMost;

            while (curr != null) {
                curr.left.next = curr.right;

                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    // iteration 写法
    public List<TreeNode> inorderTraverse(TreeNode root) {
        if (root == null) return null;
        List<TreeNode> ans = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();

        while (root != null || !queue.isEmpty()) {
            while (root != null) {
                queue.push(root);
                root = root.left;
            }
            root = queue.pop();

            ans.add(root);
            root = root.right;

        }
        return ans;
    }

    @Test
    public void test() {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), null));
        Node root2 = new Node(1,
                new Node(2, new Node(4), new Node(5)),
                new Node(3, new Node(6), null));

        System.out.println(connectV2(root2));

        List<TreeNode> list = inorderTraverse(root1);
        System.out.println("list = " + list);
    }

}
