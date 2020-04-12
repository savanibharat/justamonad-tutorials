package org.justamonad.tutorials.common;

import static java.util.Objects.requireNonNull;
import static org.justamonad.tutorials.common.JsonConverter.toJsonString;

import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {

	private final Name name;
	private final EmailAddress emailAddress;

	private Customer(EmailAddress emailAddress, Name name) {
		this.emailAddress = emailAddress;
		this.name = name;
	}

	public static Customer of(EmailAddress emailAddress, Name name) {
		return new Customer(requireNonNull(emailAddress), requireNonNull(name));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", name.toString());
		map.put("email_address", emailAddress.toString());
		return toJsonString(map);
	}

}
