package main.leetcode.structure;

/**
 * Node structure from Leetcode.
 * Exists in this repo to make importing and speed running leetcode easier
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[").append(this.val).append(",");
        ListNode temp = this;
        while (temp.next != null) {
            sb.append(temp.next.val).append(",");
            temp = temp.next;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public static ListNode createList(int... vals) {
        if (vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode temp = head;
        for (int i = 1; i < vals.length; i++) {
            temp.next = new ListNode(vals[i]);
            temp = temp.next;
        }
        return head;
    }
}
