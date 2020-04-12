package main.leetcode.advent.week2;


import main.leetcode.structure.TreeNode;

public class Day11 {
    public static void main(String[] args) {

    }

    private static int maxDepth(TreeNode curr) {
        if (curr == null) return 0;
        return 1 + Math.max(maxDepth(curr.left), maxDepth(curr.right));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        return Math.max(
                maxDepth(root.left) + maxDepth(root.right),
                Math.max(
                        diameterOfBinaryTree(root.left),
                        diameterOfBinaryTree(root.right)
                )
        );
    }
}
