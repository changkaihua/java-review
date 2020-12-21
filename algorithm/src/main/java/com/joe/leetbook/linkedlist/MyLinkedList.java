//package com.joe.leetbook.linkedlist;
//
//import java.util.List;
//
///**
// * @author ckh
// * @since 2020/12/8
// */
//public class MyLinkedList {
//
//    static class ListNode {
//        int val;
//        ListNode next;
//
//        public ListNode() {
//        }
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//
//    int size;
//    /**
//     * sentinel node as pseudo-head
//     */
//    ListNode head;
//
//    public MyLinkedList() {
//        size = 0;
//        head = new ListNode(0);
//    }
//
//    /**
//     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
//     */
//    public int get(int index) {
//        // if index is invalid
//        if (index < 0 || index >= size) {
//            return -1;
//        }
//
//        ListNode curr = head;
//        // index steps needed
//        // to move from sentinel node to wanted index
//        for (int i = 0; i < index + 1; ++i) {
//            curr = curr.next;
//        }
//        return curr.val;
//    }
//
//    /**
//     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
//     */
//    public void addAtHead(int val) {
//        addAtIndex(0, val);
//    }
//
//    /**
//     * Append a node of value val to the last element of the linked list.
//     */
//    public void addAtTail(int val) {
//        addAtIndex(size, val);
//    }
//
//    /**
//     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
//     */
//    public void addAtIndex(int index, int val) {
//        // If index is greater than the length,
//        // the node will not be inserted.
//        if (index > size) {
//            return;
//        }
//
//        // [so weird] If index is negative,
//        // the node will be inserted at the head of the list.
//        if (index < 0) {
//            index = 0;
//        }
//
//        ++size;
//        // find predecessor of the node to be added
//        ListNode pred = head;
//        for (int i = 0; i < index; ++i) {
//            pred = pred.next;
//        }
//
//        // node to be added
//        ListNode toAdd = new ListNode(val);
//        // insertion itself
//        toAdd.next = pred.next;
//        pred.next = toAdd;
//    }
//
//    /**
//     * Delete the index-th node in the linked list, if the index is valid.
//     */
//    public void deleteAtIndex(int index) {
//        // if the index is invalid, do nothing
//        if (index < 0 || index >= size) {
//            return;
//        }
//
//        size--;
//        // find predecessor of the node to be deleted
//        ListNode pred = head;
//        for (int i = 0; i < index; ++i) {
//            pred = pred.next;
//        }
//        // delete pred.next
//        pred.next = pred.next.next;
//    }
//
//}
