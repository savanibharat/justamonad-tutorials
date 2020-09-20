package com.justamonad.tutorials.properties.injection;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class PropertyFieldInjectionDiffTypes {

	@Value("${justamonad.properties.welcome-message}")
	private String message;

	@Value("${justamonad.properties.int-val}")
	private int val;

	@Value("${justamonad.properties.char-val}")
	private char charVal;

	@Value("${justamonad.properties.byte-val}")
	private byte byteVal;

	@Value("${justamonad.properties.short-val}")
	private short shortVal;

	@Value("${justamonad.properties.long-val}")
	private long longVal;

	@Value("${justamonad.properties.float-val}")
	private float floatVal;

	@Value("${justamonad.properties.double-val}")
	private double doubleVal;

	@Value("${justamonad.properties.boolean-val}")
	private boolean booleanVal;

	@Value("${justamonad.properties.bigdecimal-val}")
	private BigDecimal bigDecimalVal;

	@Value("${justamonad.properties.biginteger-val}")
	private BigInteger bigIntegerVal;

	public String getMessage() {
		return message;
	}

	public int getVal() {
		return val;
	}

	public char getCharVal() {
		return charVal;
	}

	public byte getByteVal() {
		return byteVal;
	}

	public short getShortVal() {
		return shortVal;
	}

	public long getLongVal() {
		return longVal;
	}

	public float getFloatVal() {
		return floatVal;
	}

	public double getDoubleVal() {
		return doubleVal;
	}

	public boolean isBooleanVal() {
		return booleanVal;
	}

	public BigDecimal getBigDecimalVal() {
		return bigDecimalVal;
	}

	public BigInteger getBigIntegerVal() {
		return bigIntegerVal;
	}

}
