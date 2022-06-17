package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTPreorderIterator {

    private final Deque<TreeNode> stack;

    public BSTPreorderIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        if (root != null)
            stack.push(root);
    }

    public static void main(String[] args) {
        BSTPreorderIterator bstPreorderIterator = new BSTPreorderIterator(Tree.createNodes());
        while (bstPreorderIterator.hasNext()) {
            System.out.print(bstPreorderIterator.next() + " ");
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null)
            stack.push(node.right);
        if (node.left != null)
            stack.push(node.left);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

}
