package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class UnivaluedTree {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        List<Integer> result = inorderRecursive(root);
        int val = result.get(0);
        for (Integer elem : result) {
            if (elem != val) return false;
        }
        return true;
    }

    public List<Integer> inorderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderRecursive(root, result);
        return Collections.unmodifiableList(result);
    }

    private void inorderRecursive(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderRecursive(root.left, result);
            result.add(root.val);
            inorderRecursive(root.right, result);
        }
    }

    public boolean isUnivalTreeUsingQueue(TreeNode root) {
        if (root == null) return true;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int val = root.val;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.val != val) return false;
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

        return true;
    }


    public boolean isUnivalTreeDFS(TreeNode root) {
        if (root == null) return true;
        return isUnivalTreeDFS(root, root.val);
    }

    boolean isUnivalTreeDFS(TreeNode root, int val) {
        if (root == null) return true;
        if (root.val != val) return false;
        return isUnivalTreeDFS(root.left, val) && isUnivalTreeDFS(root.right, val);

    }

}
