package com.joe.leetbook.linkedlist;

import com.joe.leetbook.tree.ListNode;

import javax.swing.text.html.HTMLWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ckh
 * @since 2020/12/22
 */
public class IntersectionNode {

    /**
     * 暴力
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        while (headA != null) {
            ListNode temp = headB;
            while (temp != null) {
                if (headA == temp) return temp;
                temp = temp.next;
            }
            headA = headA.next;
        }
        return null;
    }

    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 相交: A:  a+c  B: b+c
     * a+c +b +c == b+c +a +c  相交于 c
     * 如果 A B 不相交, 最后, 相交于 null
     */
    public ListNode getIntersectionNodeV3(ListNode headA, ListNode headB) {
        ListNode preA = headA, preB = headB;
        while (preA != preB) {
            preA = preA != null ? preA.next : headB;
            preB = preB != null ? preB.next : headA;
        }
        return preA;
    }
}
