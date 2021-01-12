package com.joe.tophot;

import com.joe.leetbook.tree.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 *
 * @author ckh
 * @since 2021/1/12
 */
public class MergeKList {
    /**
     * 顺序合并
     * 126 ms
     */
    public ListNode mergeKListsV1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeTwoList(ans, list);
        }
        return ans;
    }

    /**
     * 分治法 合并
     * 1 ms
     */
    public ListNode mergeKListsV2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;

        int mid = l + ((r - l) >> 1);
        return mergeTwoList(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 优先队列
     * 6ms
     * 真滴帅
     */
    public ListNode mergeKListV3(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        for (ListNode node : lists) {
            if (node != null) {
                q.add(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!q.isEmpty()) {
            tail.next = q.poll();
            tail = tail.next;
            if (tail.next != null) {
                q.add(tail.next);
            }
        }
        return head.next;
    }


    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1), head = ans;
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

    }
}
