package com.justamonad.tutorials.object.mapping;

import java.util.UUID;

import javax.inject.Named;

@Named
public class Invoice {

	void invoiceId() {
		System.out.println(UUID.randomUUID().toString());
	}
}
