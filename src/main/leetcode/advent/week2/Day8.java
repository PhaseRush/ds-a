package main.leetcode.advent.week2;

import main.leetcode.structure.ListNode;

public class Day8 {
    public static void main(String[] args) {
        ListNode head = ListNode.createList(1, 2, 3, 4, 5);
        System.out.println(middleNode(head));
    }

    public static ListNode middleNode(ListNode head) {
        int len = 0;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            len++;
        }
        temp = head;
        for (int i = 0; i < (len + 1) / 2; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
