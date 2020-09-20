package com.justamonad.tutorials.properties;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;

import com.justamonad.tutorials.properties.injection.PropertyConstructorInjection;
import com.justamonad.tutorials.properties.injection.PropertyFieldInjection;
import com.justamonad.tutorials.properties.injection.PropertyFieldInjectionCollection;
import com.justamonad.tutorials.properties.injection.PropertyFieldInjectionDefaultMessage;
import com.justamonad.tutorials.properties.injection.PropertyFieldInjectionDiffTypes;

@Named
public class PropertyInjectionCaller implements CommandLineRunner {

	private final ApplicationContext appContext;

	@Value("${property.type}")
	private String message;

	@Inject
	public PropertyInjectionCaller(ApplicationContext applicationContext) {
		this.appContext = applicationContext;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Property env :: " + message);
		log("constructor_injection", appContext.getBean(PropertyConstructorInjection.class).getMessage());
		log("field_injection", appContext.getBean(PropertyFieldInjection.class).getMessage());
		log("field_injection_default_message",
				appContext.getBean(PropertyFieldInjectionDefaultMessage.class).getMessage());
		log("field_injection_types", appContext.getBean(PropertyFieldInjectionDiffTypes.class));
		log("field_injection_types", appContext.getBean(PropertyFieldInjectionCollection.class));

	}

	private void log(String log, PropertyFieldInjectionCollection properties) {
		System.out.println("int[] arr :: " + Arrays.toString(properties.getArr()));
		System.out.println("List :: " + properties.getList().toString());
		System.out.println("Set :: " + properties.getSet().toString());
		System.out.println("int[] arr :: " + Arrays.toString(properties.getArrSplit()));
		System.out.println("long[] arr :: " + Arrays.toString(properties.getLongArrSplit()));
		System.out.println("double[] arr :: " + Arrays.toString(properties.getDoubleArrSplit()));
		System.out.println("char[] arr :: " + Arrays.toString(properties.getCharArrSplit()));
		System.out.println("float[] arr :: " + Arrays.toString(properties.getFloatArrSplit()));
		System.out.println("byte[] arr :: " + Arrays.toString(properties.getByteArrSplit()));
		System.out.println("BigDecimal[] arr :: " + properties.getBigDecimalSplit());
		System.out.println("BigInteger[] arr :: " + properties.getBigIntegerSplit());
		System.out.println("Map<K, List<V>> :: " + properties.getMap());
		System.out.println("Map<K, V> :: " + properties.getKeyValue());
		System.out.println("List split :: " + properties.getListSplit().toString());
		System.out.println("Set split :: " + properties.getSetSplit().toString());
	}

	private void log(String log, PropertyFieldInjectionDiffTypes properties) {
		System.out.println("String :: " + properties.getMessage());
		System.out.println("int :: " + properties.getVal());
		System.out.println("char :: " + properties.getCharVal());
		System.out.println("byte :: " + properties.getByteVal());
		System.out.println("short :: " + properties.getShortVal());
		System.out.println("long :: " + properties.getLongVal());
		System.out.println("float :: " + properties.getFloatVal());
		System.out.println("double :: " + properties.getDoubleVal());
		System.out.println("BigDecimal :: " + properties.getBigDecimalVal());
		System.out.println("BigInteger:: " + properties.getBigIntegerVal());

	}

	private void log(String log, String message) {
		System.out.println(log + " :: " + message);
	}

}
