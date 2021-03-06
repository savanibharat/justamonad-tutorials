package com.justamonad.tutorials.common;

import java.util.Collections;
import java.util.Objects;

public class EmailAddress {

	private final String emailAddress;

	private EmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public static EmailAddress of(String emailAddress) {
		return new EmailAddress(Objects.requireNonNull(emailAddress));
	}

	@Override
	public String toString() {
		return JsonConverter.toJsonString(Collections.singletonMap("emailAddress", emailAddress));
	}

}
