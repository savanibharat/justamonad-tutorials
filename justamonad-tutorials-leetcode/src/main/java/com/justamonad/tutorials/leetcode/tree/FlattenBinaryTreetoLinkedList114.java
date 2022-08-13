package com.justamonad.tutorials.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class FlattenBinaryTreetoLinkedList114 {

	public static void main(String[] args) {
		FlattenBinaryTreetoLinkedList114 f = new FlattenBinaryTreetoLinkedList114();
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(5);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(4);
		TreeNode n6 = new TreeNode(6);

		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.right = n6;
		Tree.print2D(n1);

		f.flatten(n1);
	}

	public void flatten(TreeNode root) {
		if (root == null)
			return;

		if (root.left == null && root.right == null)
			return;

		List<TreeNode> list = new ArrayList<>();
		preorder(root, list);
		TreeNode newRoot = root;
		for (int i = 1; i < list.size(); i++) {
			newRoot.left = null;
			newRoot.right = list.get(i);
			newRoot = newRoot.right;
		}
		Tree.printInorder(root);
		Tree.print2D(root);
	}

	void preorder(TreeNode root, List<TreeNode> list) {
		if (root != null) {
			list.add(root);
			preorder(root.left, list);
			preorder(root.right, list);
		}
	}

}
