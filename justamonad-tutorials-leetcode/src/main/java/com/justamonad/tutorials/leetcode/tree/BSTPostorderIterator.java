package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class BSTPostorderIterator {

    private Deque<TreeNode> stack;

    public BSTPostorderIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        init(root);
    }

    public static void main(String[] args) {
        BSTPostorderIterator iterator = new BSTPostorderIterator(Tree.createNodes());
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    private void init(TreeNode root) {
        while (root != null) {
            stack.push(root);
            if (root.left != null)
                root = root.left;
            else
                root = root.right;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        if (!stack.isEmpty()) {
            if (node == stack.peek().left) {
                // find next leaf in right sub-tree
                init(stack.peek().right);
            }
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
