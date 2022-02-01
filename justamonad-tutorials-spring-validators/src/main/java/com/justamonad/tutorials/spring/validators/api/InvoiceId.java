package com.justamonad.tutorials.spring.validators.api;

import java.util.UUID;

public final class InvoiceId {

	private final UUID uuid;

	private InvoiceId(UUID uuid) {
		this.uuid = uuid;
	}

	public static InvoiceId createInvoiceId() {
		return new InvoiceId(UUID.randomUUID());
	}

	public String invoiceId() {
		return uuid.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		InvoiceId other = (InvoiceId) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceId [" + uuid + "]";
	}

}
