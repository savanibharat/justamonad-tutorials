package com.justamonad.tutorials.response.error.mapping;

/**
 * Must be an immutable class. Reference
 * https://developers.google.com/search-ads/v2/standard-error-responses
 */
public class Error {

	private final int code;
	private final String domain;
	private final String location;
	private final String message;

	private Error(Builder builder) {
		this.code = builder.code;
		this.domain = builder.domain;
		this.location = builder.location;
		this.message = builder.message;
	}

	public int getCode() {
		return code;
	}

	public String getDomain() {
		return domain;
	}

	public String getLocation() {
		return location;
	}

	public String getMessage() {
		return message;
	}

	public static class Builder {

		private int code;
		private String domain;
		private String location;
		private String message;

		public Builder withCode(int code) {
			this.code = code;
			return this;
		}

		public Builder withDomain(String domain) {
			this.domain = domain;
			return this;
		}

		public Builder withLocation(String location) {
			this.location = location;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Error build() {
			return new Error(this);
		}

	}

}
