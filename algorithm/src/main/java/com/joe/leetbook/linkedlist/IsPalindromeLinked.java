package com.joe.leetbook.linkedlist;

import com.joe.leetbook.tree.ListNode;
import org.junit.Test;

import javax.management.ListenerNotFoundException;

/**
 * @author ckh
 * @since 2020/12/23
 */
public class IsPalindromeLinked {

    public boolean isPalindromeLinked(ListNode head) {
        int len = getLen(head);
        if (len % 2 != 0) return false;
        len /= 2;

        ListNode cur = head;
        ListNode pre = head;
        while (head.next != null && len > 0) {
            ListNode third = head.next.next;
            head.next.next = cur;
            cur = head.next;
            head.next = third;
            len--;
            pre = pre.next;
        }
        while (pre != null) {
            if (pre.val != cur.val) return false;
            pre = pre.next;
            cur = cur.next;
        }
        return true;
    }

    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1, new ListNode(2));
        System.out.println(isPalindromeLinked(head));
    }
}
