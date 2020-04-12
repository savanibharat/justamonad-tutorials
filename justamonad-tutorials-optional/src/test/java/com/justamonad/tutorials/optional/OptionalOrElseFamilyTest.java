package com.justamonad.tutorials.optional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import org.justamonad.tutorials.common.InvoiceId;
import org.justamonad.tutorials.common.Transaction;

public class OptionalOrElseFamilyTest {

	@Test
	public void test() {
		
		orElseGet(Collections.emptyList(), InvoiceId.createInvoiceId());
		
	}

	Money isPresentAndGet(List<Transaction> txns, InvoiceId invoiceId) {
		Optional<Money> optMoney = txns.stream()
				.map(txn -> txn.getInvoice())
				.filter(invoice -> invoice.invoiceId().equals(invoiceId))
				.map(invoice -> invoice.invoiceTotal())
				.findFirst();
		
		return optMoney.isPresent() 
				? optMoney.get() 
				: Money.zero(CurrencyUnit.USD);
		
	}
	
	Money orElse(List<Transaction> txns, InvoiceId invoiceId) {
		Optional<Money> optMoney = txns.stream()
				.map(txn -> txn.getInvoice())
				.filter(invoice -> invoice.invoiceId().equals(invoiceId))
				.map(invoice -> invoice.invoiceTotal())
				.findFirst();
		
		return optMoney.orElse(Money.zero(CurrencyUnit.USD));
	}
	
	Money orElseGet(List<Transaction> txns, InvoiceId invoiceId) {
		Optional<Money> optMoney = txns.stream()
				.map(txn -> txn.getInvoice())
				.filter(invoice -> invoice.invoiceId().equals(invoiceId))
				.map(invoice -> invoice.invoiceTotal())
				.findFirst();
		
		return optMoney.orElseGet(() -> Money.zero(CurrencyUnit.USD));
	}
	
	Money orElseThrow(List<Transaction> txns, InvoiceId invoiceId) {
		Optional<Money> optMoney = txns.stream()
				.map(txn -> txn.getInvoice())
				.filter(invoice -> invoice.invoiceId().equals(invoiceId))
				.map(invoice -> invoice.invoiceTotal())
				.findFirst();
		
		return optMoney.orElseThrow(InvoiceIdNotFoundException::new);
	}

	private class InvoiceIdNotFoundException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
	}
	
}
