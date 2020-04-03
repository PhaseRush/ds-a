package leetcode.linkedlists;

import leetcode.structure.ListNode;

import java.util.List;

public class DeleteNodeInLinkedList {
    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(5);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(9);
        a.next = b;
        b.next = c;
        c.next = d;

        deleteNode(b);
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        if (node.next.next == null) {
            node.next = null;
        } else {
            deleteNode(node.next);
        }
    }
}
