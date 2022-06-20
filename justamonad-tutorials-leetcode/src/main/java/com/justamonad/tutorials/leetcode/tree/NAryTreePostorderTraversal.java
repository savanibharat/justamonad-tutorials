package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NAryTreePostorderTraversal {

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node eight = new Node(8);
        Node nine = new Node(9);
        one.children.addAll(List.of(two, three, four));
        two.children.add(five);
        three.children.add(six);
        four.children.addAll(List.of(seven, eight, nine));
        /**
         *              1
         *       2      3       4
         *       5      6      7,8,9
         * */
        System.out.println(new NAryTreePostorderTraversal().postorderIterative(one));
    }

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList();
        if (root == null) return result;
        postorder(root, result);
        return result;
    }

    private void postorder(Node root, List<Integer> result) {
        if (root == null) return;
        for (Node node : root.children)
            postorder(node, result);
        result.add(root.val);
    }

    public List<Integer> postorderIterative(Node root) {
        if (root == null)
            return List.of();
        List<Integer> result = new ArrayList();
        Set<Node> visited = new HashSet<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.peek();
            if (curr.children.isEmpty()) {
                stack.pop();
                result.add(curr.val);
            } else {
                if (visited.contains(curr)) {
                    stack.pop();
                    result.add(curr.val);
                    continue;
                }
                for (int i = curr.children.size() - 1; i >= 0; i--) {
                    stack.push(curr.children.get(i));
                }
                visited.add(curr);
            }
        }
        return result;
    }

}
