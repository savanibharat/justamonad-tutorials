package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class NAryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return Collections.emptyList();

        List<List<Integer>> result = new ArrayList<>();

        Deque<Node> q = new ArrayDeque<>();
        q.add(root);

        while (!q.isEmpty()) {
            // Create results of this level.
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node n = q.poll();
                // Insert this level’s data in the list.
                list.add(n.val);
                // Add children of this level’s nodes which will be explored in the next iteration.
                q.addAll(n.children);
            }
            result.add(list);
        }
        return result;
    }

}
