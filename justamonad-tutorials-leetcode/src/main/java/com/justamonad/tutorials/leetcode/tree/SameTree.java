package com.justamonad.tutorials.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

public class SameTree {

    boolean isSameTreeRecursive(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return isSameTreeRecursive(p.left, q.left)
                && isSameTreeRecursive(p.right, q.right);
    }

    boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        Deque<TreeNode> deq = new LinkedList<>();
        deq.add(p);
        deq.add(q);

        while (!deq.isEmpty()) {
            p = deq.poll();
            q = deq.poll();

            if (p == null && q == null) continue;

            if (!isValid(p, q)) {
                return false;
            }
            deq.add(p.left);
            deq.add(q.left);
            deq.add(p.right);
            deq.add(q.right);
        }
        return true;
    }

    private boolean isValid(TreeNode p, TreeNode q) {
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

}
