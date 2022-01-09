package com.justamonad.tutorials.leetcode.tree;

public class Tree {

	public static TreeNode createNodes() {
		TreeNode n1 = new TreeNode(50);
		TreeNode n2 = new TreeNode(30);
		TreeNode n3 = new TreeNode(80);

		n1.left = n2;
		n1.right = n3;

		TreeNode n4 = new TreeNode(20);
		TreeNode n5 = new TreeNode(35);
		n2.right = n5;
		n2.left = n4;

		TreeNode n6 = new TreeNode(70);
//	Node n61 = new Node(69);
//	Node n62 = new Node(68);
//	Node n63 = new Node(67);
//	Node n64 = new Node(66);
		TreeNode n7 = new TreeNode(90);
		n3.left = n6;
//	n3.left.left = n61;
//	n3.left.left.left = n62;
//	n3.left.left.left.left = n63;
//	n3.left.left.left.left.left = n64;
		n3.right = n7;
		print2D(n1);
		return n1;
	}

	public static TreeNode createNodesPlus1() {
		TreeNode n1 = new TreeNode(51);
		TreeNode n2 = new TreeNode(31);
		TreeNode n3 = new TreeNode(81);

		n1.left = n2;
		n1.right = n3;

		TreeNode n4 = new TreeNode(21);
		TreeNode n5 = new TreeNode(36);
		n2.right = n5;
		n2.left = n4;

		TreeNode n6 = new TreeNode(71);
//	Node n61 = new Node(69);
//	Node n62 = new Node(68);
//	Node n63 = new Node(67);
//	Node n64 = new Node(66);
		TreeNode n7 = new TreeNode(91);
		n3.left = n6;
//	n3.left.left = n61;
//	n3.left.left.left = n62;
//	n3.left.left.left.left = n63;
//	n3.left.left.left.left.left = n64;
		n3.right = n7;
		print2D(n1);
		return n1;
	}

	public static TreeNode create01Nodes() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(0);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(0);
		TreeNode n5 = new TreeNode(1);
		TreeNode n6 = new TreeNode(0);
		TreeNode n7 = new TreeNode(1);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		return n1;
	}

//	static class TreeNode {
//		int data;
//		TreeNode left, right;
//		TreeNode next;
//
//		TreeNode(int item) {
//			data = item;
//			left = null;
//			right = null;
//			next = null;
//		}
//
//		@Override
//		public String toString() {
//			return data + " ";
//		}
//
//	}

	static void printInorder(TreeNode node) {
		if (node == null)
			return;

		/* first recur on left child */
		printInorder(node.left);

		/* then print the data of node */
		System.out.print(node.val + " ");

		/* now recur on right child */
		printInorder(node.right);
	}

	static final int COUNT = 5;

	public static void print2DUtil(TreeNode root, int space) {
		// Base case
		if (root == null)
			return;

		// Increase distance between levels
		space += COUNT;

		// Process right child first
		print2DUtil(root.right, space);

		// Print current node after space
		// count
		System.out.print("\n");
		for (int i = COUNT; i < space; i++)
			System.out.print(" ");
		System.out.print(root.val + "\n");

		// Process left child
		print2DUtil(root.left, space);
	}

	// Wrapper over print2DUtil()
	public static void print2D(TreeNode root) {
		// Pass initial space count as 0
		print2DUtil(root, 0);
	}

}
