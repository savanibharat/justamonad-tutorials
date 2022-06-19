package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class PostorderTraversal {

    public static void main(String[] args) {
        postorderRecursive(Tree.createNodes());
    }

    public static List<Integer> postorderRecursive(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderRecursive(root, list);
        System.out.println(list);
        return list;
    }

    private static void postorderRecursive(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorderRecursive(root.left, list);
            postorderRecursive(root.right, list);
            list.add(root.val);
        }
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = postorderTraversalIterative(root);
        System.out.println(list);
        return list;
    }

    public static List<Integer> postorderTraversalIterative(TreeNode root) {

        if (root == null) return Collections.emptyList();

        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode curr = root;
        stack.push(curr);
        init(curr, stack);

        while (!stack.isEmpty()) {
            System.out.println("Before pop:: " + stack);
            TreeNode node = stack.pop();
            System.out.println("After pop:: " + stack);
            // We have added root twice so we can go to right subtree.
            if (stack.isEmpty()) continue;
            System.out.println("Added to result:: " + node.val);
            result.add(node.val);
            if (node == stack.peek().left) {
                // find next leaf in right sub-tree
                init(stack.peek().right, stack);
            }
        }
        return result;
    }

    private static void init(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            if (root.left != null) root = root.left;
            else root = root.right;
        }
    }

}
