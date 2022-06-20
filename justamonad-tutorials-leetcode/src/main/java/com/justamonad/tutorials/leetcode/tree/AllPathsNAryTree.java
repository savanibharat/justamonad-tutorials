package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class AllPathsNAryTree {

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
        System.out.println(allPathsOfABinaryTree(one));
    }

    static List<List<Integer>> allPathsOfABinaryTree(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        allPaths(root, path, result);
        return result;
    }

    static void allPaths(Node root, List<Integer> path, List<List<Integer>> result) {
        if (root == null)
            return;
        path.add(root.val);
        if (root.children.isEmpty()) {
            result.add(new ArrayList<>(path));
        }
        for (Node child : root.children) {
            allPaths(child, path, result);
        }
        path.remove(path.size() - 1);
    }

}
