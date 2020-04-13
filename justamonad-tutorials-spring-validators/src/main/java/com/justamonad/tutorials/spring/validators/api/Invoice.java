package com.justamonad.tutorials.spring.validators.api;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

	private final LocalDate date;
	private final InvoiceId invoiceId;
	private final List<Item> items;

	private Invoice(final LocalDate date, final List<Item> items, final InvoiceId invoiceId) {
		this.date = date;
		this.items = items;
		this.invoiceId = invoiceId;
	}

	public static Invoice of(final LocalDate date, final List<Item> items) {
		return new Invoice(date, items, InvoiceId.createInvoiceId());
	}

	public LocalDate date() {
		return date;
	}

	public InvoiceId invoiceId() {
		return invoiceId;
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
		StringBuilder builder = new StringBuilder();
		builder.append("Invoice [date=");
		builder.append(date);
		builder.append(", invoiceId=");
		builder.append(invoiceId);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

}
