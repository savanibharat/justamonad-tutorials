package com.justamonad.tutorials.common;

import static com.justamonad.tutorials.common.JsonConverter.toJsonString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.joda.money.CurrencyUnit;

import com.neovisionaries.i18n.CountryCode;

/**
 * toString must be in json.
 */
public class Transaction {

	private final Invoice invoice;
	private final BigDecimal amount;
	private final CurrencyUnit currency;
	private final CountryCode country;
	private final LocalDate date;
	private final long transactionId;
	private final Random random = new Random();

	private Transaction(final String country, final Invoice invoice) {
		this.invoice = invoice;
		this.amount = invoice.invoiceTotal().getAmount();
		this.currency = invoice.invoiceTotal().getCurrencyUnit();
		this.country = CountryCode.getByCode(country);
		this.date = invoice.date();
		transactionId = Math.abs(random.nextLong());

	}

	public static Transaction of(final String country, final Invoice invoice) {
		Objects.requireNonNull(country);
//		Objects.requireNonNull(invoice);
		return new Transaction(country, invoice);
	}

	public Invoice invoice() {
		return invoice;
	}

	public BigDecimal amount() {
		return amount;
	}

	public CurrencyUnit currency() {
		return currency;
	}

	public CountryCode country() {
		return country;
	}

	public LocalDate date() {
		return date;
	}
	
	public long transactionId() {
		return transactionId;
	}

	@Override
	public String toString() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("invoice", invoice.toString());
		return toJsonString(map);
	}

}
