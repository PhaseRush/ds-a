package main.leetcode.advent.week2;

import main.leetcode.structure.ListNode;

public class Day8 {
    public static void main(String[] args) {

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
