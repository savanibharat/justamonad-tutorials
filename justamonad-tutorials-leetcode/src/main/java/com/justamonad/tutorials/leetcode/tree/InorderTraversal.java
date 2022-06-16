package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class InorderTraversal {

    public List<Integer> inorderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return Collections.unmodifiableList(result);
    }

    private void inorderRecursive(TreeNode root, List<Integer> result) {
        if(root != null) {
            inorderRecursive(root.left, result);
            result.add(root.val);
            inorderRecursive(root.right, result);
        }
    }

    public List<Integer> inorder(TreeNode root) {
        if (root == null)
            return Collections.emptyList();

        if (root.left == null && root.right == null)
            return Collections.singletonList(root.val);

        TreeNode curr = root;
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                // Push left subtree in stack.
                stack.push(curr);
                curr = curr.left;
            }
            // Visit the node as no left subtree exists.
            curr = stack.pop();
            result.add(curr.val);
            // Visit the right subtree
            curr = curr.right;
        }
        return Collections.unmodifiableList(result);
    }

}
