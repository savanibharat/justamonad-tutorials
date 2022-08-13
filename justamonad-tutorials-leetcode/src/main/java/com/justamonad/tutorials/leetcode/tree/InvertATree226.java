package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class InvertATree226 {

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        stack.push(curr);
        while (!stack.isEmpty()) {
            curr = stack.pop();
            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode n = Tree.createNodes();
        invertTree(n);
        System.out.println("---------------------------------------------------------");
        Tree.print2D(n);
    }

    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) return null;

        /**
         * Recursion can be for right and then left subtree or vice-versa.
         * */
        TreeNode right = invertTreeRecursive(root.right);
        TreeNode left = invertTreeRecursive(root.left);

        root.left = right;
        root.right = left;

        return root;
    }

}
