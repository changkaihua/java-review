package com.joe.leetbook.tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ckh
 * @create 11/6/20 3:27 PM
 */
public class FillTreeNextNode {
    /**
     * 完美二叉树与一般二叉树均可以实现
     */
    public Node connect(Node root) {
        if (root == null) return null;

        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                // 连接
                if (i < size - 1) {
                    curr.next = queue.peek();
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return root;
    }


    /**
     * 只能基于完美二叉树
     */
    public Node connectMethod2(Node root) {
        if (root == null) return root;

        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // 指针向后移动
                head = head.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }


    /**
     * 基于一般二叉树
     */
    public Node connectMethod3(Node root) {
        if (root == null) return root;
        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null ) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // 指针向后移动
                head = head.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }
        return root;
    }


    @Test
    public void test() {

        Node root = new Node(1,
                new Node(2, new Node(4), new Node(5), null),
                new Node(3, new Node(6), new Node(7), null),
                null);

        System.out.println(connect(root));
    }
}
