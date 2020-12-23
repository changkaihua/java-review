package com.joe.leetbook.linkedlist;

import com.joe.leetbook.tree.ListNode;

/**
 * @author ckh
 * @since 2020/12/23
 */
public class OddEvenList {
    public ListNode oddEvenList(ListNode head) {
        ListNode odd = new ListNode(0), oddTail = odd,
                even = new ListNode(0), evenTail = even;

        boolean flag = true;
        while (head != null) {
            if (flag) {
                oddTail.next = head;
                oddTail = oddTail.next;
            } else {
                evenTail.next = head;
                evenTail = evenTail.next;
            }
            head = head.next;
            flag = !flag;
        }
        oddTail.next = even.next;
        evenTail.next = null;
        return odd.next;

    }
}
