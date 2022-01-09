package com.justamonad.tutorials.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SymmetricTree101 {

	public static void main(String[] args) {
		TreeNode n = new TreeNode(1);
		n.left = new TreeNode(2);
		n.right = new TreeNode(2);
		n.left.left = new TreeNode(3);
		n.left.right = new TreeNode(4);
		n.right.left = new TreeNode(4);
		n.right.right = new TreeNode(3);
		Tree.print2D(n);
		System.out.println(isSymmetricTreeIterative(n));
		System.out.println(isSymmetricTreeRecursive(n));
		System.out.println(isSymmetricUsingQueue(n));
	}

	/**
	 * Time: O(N) because we traverse the entire input tree once. 
	 * Space: O(h)
	 * */
	private static boolean isSymmetricTreeIterative(TreeNode root) {
		Stack<TreeNode> s1 = new Stack<>();
		s1.push(root);
		s1.push(root);
		while (!s1.isEmpty()) {

			TreeNode t1 = s1.pop();
			TreeNode t2 = s1.pop();

			if (t1 == null && t2 == null)
				continue;
			if (t1 == null || t2 == null)
				return false;
			if (t1.val != t2.val)
				return false;

			s1.push(t1.left);
			s1.push(t2.right);
			s1.push(t1.right);
			s1.push(t2.left);

			System.out.println(s1);

		}
		return true;
	}

	/**
	 * Time: O(N) because we traverse the entire input tree once. 
	 * Space: O(h)
	 * */
	private static boolean isSymmetricTreeRecursive(TreeNode root) {
		// O(h) time
		if (root == null)
			return true;
		return checkSymmetric(root.left, root.right);
	}

	private static boolean checkSymmetric(TreeNode left, TreeNode right) {
		// Base Case
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		if (left.val != right.val)
			return false;

		return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
	}

	/**
	 * Time: O(N)
	 * Space: O(w) where w is the maximum number TreeNodes in a level of the tree.
	 * */
	private static boolean isSymmetricUsingQueue(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root.left);
		queue.offer(root.right);

		while (queue.size() > 0) {
			System.out.println(queue);
			TreeNode t1 = queue.poll();
			TreeNode t2 = queue.poll();
			// check
			if (t1 == null && t2 == null)
				continue;
			if (t1 == null || t2 == null)
				return false;
			if (t1.val != t2.val)
				return false;
			// offer children
			queue.offer(t1.left);
			queue.offer(t2.right);

			queue.offer(t1.right);
			queue.offer(t2.left);
			
		}
		return true;
	}

}

