package com.justamonad.tutorials.collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterProperty {

    @Test
    public void configPropertyTest() {
        List<ConfigProperty> configProperties = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            configProperties.add(new ConfigProperty().withKey("Key" + i).withValue("Value" + i));
        }

        getProperty(configProperties);

    }
    static final BiPredicate<ConfigProperty, String> f1 = (configProp, attributeName)
            -> configProp.getKey().equals(attributeName) && configProp.getValue() != null;
    public static void getProperty(List<ConfigProperty> configProperties) {
        final String key1 = "Key1";
        final String value1 = "Value1";

        final String key2 = "Key2";
        final String value2 = "Value2";

        PropertyResultClass propertyResultClass = new PropertyResultClass();
        for (ConfigProperty property : configProperties) {
            if (f1.test(property, key1)) {
                propertyResultClass.setVal1(value1);
            } else if (f1.test(property, key2)) {
                propertyResultClass.setVal2(value2);
            }
        }
        System.out.println(propertyResultClass);

    }

}
