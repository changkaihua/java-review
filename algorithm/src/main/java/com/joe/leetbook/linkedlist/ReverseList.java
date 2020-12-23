package com.joe.leetbook.linkedlist;

import com.joe.leetbook.tree.ListNode;

import java.util.List;

/**
 * @author ckh
 * @since 2020/12/23
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        while (head.next != null) {
            ListNode third = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = third;
        }
        return cur;

//        ListNode prev = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode nextTemp = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = nextTemp;
//        }
//        return prev;
    }

    public ListNode reverseListV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseListV2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


}
