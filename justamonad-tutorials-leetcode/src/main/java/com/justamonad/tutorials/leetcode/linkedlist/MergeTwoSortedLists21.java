package com.justamonad.tutorials.leetcode.linkedlist;

public class MergeTwoSortedLists21 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode result = new ListNode();
		ListNode pointer = result;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				pointer.next = l1;
				pointer = pointer.next;
				l1 = l1.next;
			} else {
				pointer.next = l2;
				pointer = pointer.next;
				l2 = l2.next;
			}
		}

		pointer.next = l1 != null ? l1 : l2;

		return result.next;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

}
