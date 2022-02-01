package com.justamonad.tutorials.spring.validators.api;

public final class Transaction {

	private final Customer customer;
	private final Merchant merchant;
	private final Invoice invoice;

	private Transaction(final Customer customer, final Merchant merchant, final Invoice invoice) {
		this.customer = customer;
		this.merchant = merchant;
		this.invoice = invoice;
	}

	public static Transaction of(final Customer customer, final Merchant merchant, final Invoice invoice) {
		return new Transaction(customer, merchant, invoice);
	}

	public Customer customer() {
		return customer;
	}

	public Merchant merchant() {
		return merchant;
	}

	public Invoice invoice() {
		return invoice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
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
		Transaction other = (Transaction) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (merchant == null) {
			if (other.merchant != null)
				return false;
		} else if (!merchant.equals(other.merchant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [customer=");
		builder.append(customer);
		builder.append(", merchant=");
		builder.append(merchant);
		builder.append(", invoice=");
		builder.append(invoice);
		builder.append("]");
		return builder.toString();
	}

}
