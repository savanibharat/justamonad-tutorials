package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.api.Invoice;
import com.justamonad.tutorials.spring.validators.api.Item;
import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public class ItemValidator implements ValidatorFunction {

	@Inject
	private ValidatorErrorBeans validatorErrorBeans;

	private static final Predicate<Invoice> 
	INVOICE_ITEM_PREDICATE = (invoice) -> !(invoice.items() == null || invoice.items().isEmpty());

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		if (transaction != null && transaction.invoice() != null
				&& INVOICE_ITEM_PREDICATE.test(transaction.invoice())) {
			return transaction.invoice().items()
					.stream()
					.map(Item::price)
					.filter(Objects::isNull)
					.findAny()
					.map(money -> validatorErrorBeans.noAmount())
					.orElseGet(Collections::emptyList);
		}
		return Collections.emptyList();
	}
	
}
