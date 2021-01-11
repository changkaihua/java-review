package com.joe.tophot;

import com.joe.leetbook.tree.ListNode;
import org.junit.Test;

/**
 * 21. 合并两个有序链表
 *
 * @author ckh
 * @since 2021/1/11
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        if (l1 == null && l2 == null) return null;
//        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
//        // make the l1 is smaller
//        if (l1.val > l2.val) {
//            ListNode temp = l1;
//            l1 = l2;
//            l2 = temp;
//        }
        ListNode ans = new ListNode(-1),  head = ans;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ans.next = l2;
                l2 = l2.next;
            } else {
                ans.next = l1;
                l1 = l1.next;
            }
            ans = ans.next;
        }
        ans.next = l1 == null ? l2 : l1;
        return head.next;
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(3, new ListNode(7, new ListNode(9, new ListNode(26))));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = mergeTwoLists(l1, l2);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
