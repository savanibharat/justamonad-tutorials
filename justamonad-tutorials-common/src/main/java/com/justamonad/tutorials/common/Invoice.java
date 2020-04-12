package com.justamonad.tutorials.common;

import static com.justamonad.tutorials.common.JsonConverter.toJsonString;
import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.joda.money.CurrencyUnit.USD;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class Invoice {

	private final LocalDate date;
	private final InvoiceId invoiceId;
	private final List<Item> items;
	private final Money invoiceTotal;

	private Invoice(final LocalDate date, final List<Item> items, final InvoiceId invoiceId, final Money invoiceTotal) {
		this.date = date;
		this.items = items;
		this.invoiceId = invoiceId;
		this.invoiceTotal = invoiceTotal;
	}

	public static Invoice of(final LocalDate date, final List<Item> items) {
		Objects.requireNonNull(date);
		Objects.requireNonNull(items);

		List<Item> itemsCopy = items.stream().collect(collectingAndThen(toList(), Collections::unmodifiableList));

		Money money = Money.of(USD, ZERO);
		if (!itemsCopy.isEmpty()) {
			CurrencyUnit currencyUnit = itemsCopy.get(0).price().getCurrencyUnit();
			Money identity = Money.of(currencyUnit, ZERO);
			money = itemsCopy.stream().map(item -> item.price()).reduce(identity, Money::plus);
		}
		return new Invoice(date, itemsCopy, InvoiceId.createInvoiceId(), money);
	}

	public LocalDate date() {
		return date;
	}

	public InvoiceId invoiceId() {
		return invoiceId;
	}

	public Money invoiceTotal() {
		return invoiceTotal;
	}

	public List<Item> items() {
		return items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("invoice_date", date.toString());
		map.put("invoice_id", invoiceId.toString());
		map.put("tems", items.toString());
		map.put("invoice_total", invoiceTotal.getAmount());
		return toJsonString(map);
	}

}
