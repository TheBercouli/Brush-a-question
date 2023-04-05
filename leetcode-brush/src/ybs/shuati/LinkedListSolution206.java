package ybs.shuati;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class LinkedListSolution206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseList206v1(ListNode head) {

        if (head == null)
            return null;
        ListNode pre = null; // 辅助指针1
        ListNode cur = head; // 辅助指针2
        while (cur != null) {
            ListNode next = cur.next; // 辅助指针3

            // 反转操作
            cur.next = pre;
            // 移动辅助指针
            pre = cur;
            cur = next;
        }
        return pre;
    }
    // 1->2->3->4->5
    public ListNode reverseList206v2(ListNode head) {
        // 递归结束条件
        if (head == null || head.next == null)
            return head;
        ListNode newNode = reverseList206v2(head.next);
        // 这里假设我们的递归操作是正确的，那么递归结束后我们要考虑可能出现的问题
        // 递归结束后，1->2<-3<-4<-5,因此我们应该对最开始head.next做处理
        head.next.next = head; // 让 1<-2 与此同时 1->2 存在
        head.next = null; // 断掉1->2
        return newNode;
    }
    public ListNode reverseList206v3(ListNode head) {
        if (head == null)
            return null;
        ListNode cur = head; // 指针
        while (head.next != null) {
            ListNode nextNextNode = head.next.next;
            head.next.next = cur; // 反转操作
            cur = head.next; // 移动指针1
            head.next = nextNextNode; // 移动指针2
        }
        return cur;

    }

    //92
    public ListNode reverseBetween92(ListNode head, int left, int right) {
        ArrayList<ListNode> listNodes = new ArrayList<>();
        return null;
    }
    //83 86 328 2 445


    /**
     * 203:移除链表元素 虚拟头节点
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     */
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyNode = new ListNode(-1, head);
        ListNode cur = dummyNode;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
                delNode.next = null;
            }else {
                cur = cur.next;
            }
        }
        ListNode resHeadNode = dummyNode.next;
        dummyNode.next = null;
        return resHeadNode;

    }
    // 82 21

    /**
     * 24：两两交换链表中的节点-虚拟头节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
     * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode p = dummyNode;
        while (p.next != null && p.next.next != null) {

            ListNode node1 = p.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            node2.next = node1;
            node1.next = next;
            p.next = node2;

            // 循环增长条件
            p = node1;
        }
        ListNode retNode = dummyNode.next;
        dummyNode.next = null;
        return retNode;
    }
    //25 [147 148]

    /**
     * 237:删除链表中的节点 - 不一定所有的链表都需要穿针引线，可以使用覆盖的方式解决部分问题，不能死板
     * 有一个单链表的head，我们想删除它其中的一个节node。
     *
     * 给你一个需要删除的节node。你将无法访问第一个节点head。
     *
     * 链表的所有值都是 唯一的，并且保证给定的节点node不是链表中的最后一个节点。
     */
    public void deleteNode237(ListNode node) {
        if (node == null)
            return;
        if (node.next == null) {
            node = null;
            return;
        }

        ListNode dummyNode = node;
        dummyNode.val = dummyNode.next.val;
        ListNode delNode = dummyNode.next;
        dummyNode.next = delNode.next;
        delNode.next = null;
    }

    /**
     * 19：删除链表的倒数第 N 个结点
     */
    public ListNode removeNthFromEnd19v1(ListNode head, int n) {
        int length = 0;
        ListNode temp = head;
        while (temp != null) {
            ++length;
            temp = temp.next;
        }
        ListNode dummyNode = new ListNode(0, head);
        ListNode cur = dummyNode;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next; //让指针走到待删除位置
        }
        // 此时待删除元素位cur.next
        ListNode delNode = cur.next;
        cur.next = delNode.next;
        delNode.next = null;
        //cur.next = cur.next.next;

        ListNode retNode = dummyNode.next;
        dummyNode.next = null;
        return retNode;
    }
    public ListNode removeNthFromEnd19v2(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1, head);

        // 双指针：开始时位置相同
        ListNode start = dummyNode;
        ListNode end = dummyNode;
        // 处理节点位置：倒数第n个元素意味着，当end指向尾部null了，那么start就指向了待删元素的前一个元素
        for (int i = 0; i < n + 1; i++) {
            end = end.next; // 与start相隔n的距离，之后一起滑动，就能找到目的的start位置
        }

        // 一起滑动
        while (end != null) {
            start = start.next;
            end = end.next;
        }

        // 此时找到start即待删除节点的前一个节点
        ListNode delNode = start.next;
        start.next = delNode.next;
        delNode.next = null;

        ListNode ret = dummyNode.next;
        dummyNode.next = null;

        return ret;
    }

    // 61 143 234


}
