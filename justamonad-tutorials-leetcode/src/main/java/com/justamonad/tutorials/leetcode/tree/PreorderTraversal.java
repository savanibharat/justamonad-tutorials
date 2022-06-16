package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class PreorderTraversal {

    public List<Integer> preorderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return Collections.unmodifiableList(result);
    }

    private void preorderRecursive(TreeNode root, List<Integer> result) {
        if(root != null) {
            result.add(root.val);
            preorderRecursive(root.left, result);
            preorderRecursive(root.right, result);
        }
    }


    public List<Integer> preorder(TreeNode root) {
        TreeNode curr = root;
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                list.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }
        return list;
    }

}
