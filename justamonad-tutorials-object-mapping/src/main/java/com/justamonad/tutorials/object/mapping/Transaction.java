package com.justamonad.tutorials.object.mapping;

import javax.inject.Named;

@Named
public class Transaction {

	void txn() {
		System.out.println("In Transaction class.");
	}
}
