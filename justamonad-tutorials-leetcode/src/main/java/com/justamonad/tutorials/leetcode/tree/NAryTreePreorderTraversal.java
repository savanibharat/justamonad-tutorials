package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class NAryTreePreorderTraversal {

    public List<Integer> preorder(Node root) {
        if (root == null) return List.of();
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return Collections.unmodifiableList(list);
    }

    private void preorder(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            for (Node n : root.children) {
                preorder(n, list);
            }
        }
    }

    public List<Integer> preorderIterative(Node root) {
        if (root == null) return List.of();

        List<Integer> result = new ArrayList<>();

        Deque<Node> stack = new ArrayDeque<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            result.add(curr.val);
            for (int i = curr.children.size() - 1; i >= 0; i--) {
                stack.push(curr.children.get(i));
            }
        }
        return result;
    }

}
