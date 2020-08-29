package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import com.justamonad.tutorials.spring.validators.api.Item;
import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public class ItemValidator implements ValidatorFunction {

	private final List<ErrorData> noItems;
	private final List<ErrorData> noAmount;

	@Inject
	public ItemValidator(
			@Named("emptyItems") List<ErrorData> noItems, 
			@Named("emptyAmount") List<ErrorData> noAmount) {
		this.noItems = noItems;
		this.noAmount = noAmount;
	}

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		
		if(transaction.invoice().items() == null
				|| transaction.invoice().items().isEmpty()) {
			return noItems;
		}
		
		return transaction.invoice().items()
				.stream()
				.map(Item::price)
				.filter(Objects::isNull)
				.findAny().map(money -> noAmount).orElseGet(Collections::emptyList);
		
	}

}
