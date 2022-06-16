package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

class BSTIterator {

    private final Deque<TreeNode> stack;
    private TreeNode temp;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        temp = root;
        pushLeftSubTree(temp);
    }

    void pushLeftSubTree(TreeNode temp) {
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
    }

    public int next() {
        temp = stack.pop();
        pushLeftSubTree(temp.right);
        return temp.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
