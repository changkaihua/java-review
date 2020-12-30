package com.joe.tophot;

import com.joe.leetbook.tree.ListNode;
import org.junit.Test;

/**
 * 2. 两数相加
 *
 * @author ckh
 * @since 2020/12/29
 */
public class TowNumbers {

    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {

        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int bit = 0;
        ListNode ans = null, temp = null;

        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + bit;
            if (ans == null) {
                ans = new ListNode(sum % 10);
                temp = ans;
            } else {
                temp.next = new ListNode(sum % 10);
                temp = temp.next;
            }
            bit = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (bit != 0) {
            temp.next = new ListNode(bit);
        }
        return ans;
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        ListNode node = addTwoNumbers(l1, l2);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }

    }
}
