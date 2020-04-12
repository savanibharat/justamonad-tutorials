package org.justamonad.tutorials.common;

import static org.justamonad.tutorials.common.JsonConverter.toJsonString;

import java.util.Collections;
import java.util.UUID;

public class InvoiceId {

	private final UUID uuid;

	private InvoiceId(UUID uuid) {
		this.uuid = uuid;
	}

	public static InvoiceId createInvoiceId() {
		return new InvoiceId(UUID.randomUUID());
	}

	public UUID invoiceId() {
		return uuid;
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
		return toJsonString(Collections.singletonMap("invoice_id", uuid.toString()));
	}

}
