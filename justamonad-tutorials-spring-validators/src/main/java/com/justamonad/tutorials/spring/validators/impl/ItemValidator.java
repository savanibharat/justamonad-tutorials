package com.justamonad.tutorials.spring.validators.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.util.CollectionUtils;

import com.justamonad.tutorials.spring.validators.api.Item;
import com.justamonad.tutorials.spring.validators.api.Transaction;

@Named
public final class ItemValidator implements ValidatorFunction {

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
		if(isInvoiceItemsNotAvailable(transaction)) {
			return noItems;
		}
		if (isItemPriceNotAvailable(transaction)) {
			return noAmount;
		}
		return Collections.emptyList();
	}

	private boolean isInvoiceItemsNotAvailable(Transaction transaction) {
		return transaction == null 
	            || transaction.invoice() == null
	            || CollectionUtils.isEmpty(transaction.invoice().items());
	}

	private boolean isItemPriceNotAvailable(Transaction transaction) {
		return transaction
		            .invoice()
		            .items()
		            .stream()
		            .map(Item::price)
		            .anyMatch(Objects::isNull);
	}

}
