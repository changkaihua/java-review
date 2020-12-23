package com.joe.leetbook.linkedlist;

import com.joe.leetbook.tree.ListNode;
import org.junit.Test;

/**
 * @author ckh
 * @since 2020/12/23
 */
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode sentinel = new ListNode(0, head);
        ListNode pre = sentinel, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return sentinel.next;
    }

    public ListNode removeElementsV2(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElementsV2(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    @Test
    public void test() {
//        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode head = new ListNode(1, new ListNode(1));
        ListNode listNode = removeElements(head, 1);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
