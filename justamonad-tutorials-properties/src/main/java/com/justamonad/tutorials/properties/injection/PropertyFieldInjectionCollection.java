package com.justamonad.tutorials.properties.injection;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named
public class PropertyFieldInjectionCollection {

	@Value("${justamonad.properties.int-vals}")
	private int[] arr;

	@Value("${justamonad.properties.int-vals}")
	private List<Integer> list;

	@Value("${justamonad.properties.int-vals}")
	private Set<Integer> set;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private int[] arrSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private List<Integer> listSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private Set<Integer> setSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private float[] floatArrSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private byte[] byteArrSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private char[] charArrSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private long[] longArrSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private long[] doubleArrSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private List<BigInteger> bigIntegerSplit;

	@Value("#{'${justamonad.properties.int-vals-seperator}'.split(';')}")
	private List<BigDecimal> bigDecimalSplit;

	@Value("#{${justamonad.properties.map1}}")
	private Map<String, List<String>> keyListValue;

	@Value("#{${justamonad.properties.map2}}")
	private Map<String, String> keyValue;

//	@Value("#{${justamonad.properties.map3}}")
//	private Map<String, String> currency;
	
//	public Map<String, String> getCurrency() {
//		return currency;
//	}

	public Map<String, String> getKeyValue() {
		return keyValue;
	}

	public Map<String, List<String>> getMap() {
		return keyListValue;
	}

	public char[] getCharArrSplit() {
		return charArrSplit;
	}

	public List<BigDecimal> getBigDecimalSplit() {
		return bigDecimalSplit;
	}

	public List<BigInteger> getBigIntegerSplit() {
		return bigIntegerSplit;
	}

	public long[] getDoubleArrSplit() {
		return doubleArrSplit;
	}

	public long[] getLongArrSplit() {
		return longArrSplit;
	}

	public byte[] getByteArrSplit() {
		return byteArrSplit;
	}

	public float[] getFloatArrSplit() {
		return floatArrSplit;
	}

	public int[] getArr() {
		return arr;
	}

	public List<Integer> getList() {
		return list;
	}

	public Set<Integer> getSet() {
		return set;
	}

	public List<Integer> getListSplit() {
		return listSplit;
	}

	public Set<Integer> getSetSplit() {
		return setSplit;
	}

	public int[] getArrSplit() {
		return arrSplit;
	}

}
