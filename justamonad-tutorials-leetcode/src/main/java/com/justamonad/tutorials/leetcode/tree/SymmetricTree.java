package com.justamonad.tutorials.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

public class SymmetricTree {

    public boolean isSymmetricRecursive(TreeNode root) {
        if (root == null) return true;
        return isSymmetricRecursive(root.left, root.right);
    }

    private boolean isSymmetricRecursive(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) return true;

        if (leftNode == null || rightNode == null) return false;

        if (leftNode.val == rightNode.val) {
            return isSymmetricRecursive(leftNode.left, rightNode.right)
                    && isSymmetricRecursive(leftNode.right, rightNode.left);
        }
        return false;
    }

    public boolean isSymmetric(TreeNode root) {

        if (root == null) return true;

        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode leftNode = q.poll();
            TreeNode rightNode = q.poll();
            if (leftNode == null && rightNode == null) continue;

            if (leftNode == null || rightNode == null) return false;

            if (leftNode.val != rightNode.val) return false;

            q.add(leftNode.left);
            q.add(rightNode.right);

            q.add(leftNode.right);
            q.add(rightNode.left);
        }
        return true;
    }

}
