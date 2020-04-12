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
final class ItemValidator implements ValidatorFunction {

	@Inject
	private ValidatorErrorBeans validatorErrorBeans;

	@Override
	public List<ErrorData> apply(Transaction transaction) {
		if (transaction != null && transaction.invoice() != null
				&& !CollectionUtils.isEmpty(transaction.invoice().items())) {
			return transaction.invoice().items().stream()
					.map(Item::price)
					.filter(Objects::isNull)
					.findAny()
					.map(money -> validatorErrorBeans.noAmount())
					.orElseGet(Collections::emptyList);
		}
		return Collections.emptyList();
	}

}
