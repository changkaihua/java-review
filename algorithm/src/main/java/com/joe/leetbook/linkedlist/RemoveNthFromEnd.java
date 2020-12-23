package com.joe.leetbook.linkedlist;

import com.joe.leetbook.tree.ListNode;
import com.joe.leetcode.part0.Permutations;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author ckh
 * @since 2020/12/23
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = pre;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = pre.next;
        return ans;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        Deque<ListNode> stack = new ArrayDeque<>();

        ListNode curr = pre;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode del = stack.peek();
        del.next = del.next.next;
        return pre.next;
    }

    public ListNode removeNthFromEndV3(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        ListNode fast = head;
        ListNode slow = pre;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }

    @Test
    public void test() {
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head = new ListNode(1);
        System.out.println(removeNthFromEnd(head, 1));
    }
}
