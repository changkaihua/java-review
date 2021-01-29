package com.joe.tophot;

import com.joe.leetbook.tree.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表
 *
 * @author ckh
 * @since 2021/1/28
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int left = 0, right = list.size() - 1;

        while (left < right) {
            if (!list.get(left).equals(list.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }


    public boolean isPalindromeV2(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head, p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
