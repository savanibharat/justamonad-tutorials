package com.justamonad.tutorials.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterProperty {

    public static void main(String[] args) {
        List<ConfigProperty> configProperties = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            configProperties.add(new ConfigProperty().withKey("Key" + i).withValue("Value" + i));
        }

        getProperty(configProperties);
    }

    public static void getProperty(List<ConfigProperty> configProperties) {
        final String key = "Key1";
        final String value = "Value1";
        ConfigProperty result = null;
        for (ConfigProperty property : configProperties) {
            if(property.getKey().equals(key) && property.getValue().equals(value)) {
                result = property;
            }
        }
        System.out.println(result);
        //how to set multiple properties
        List<ConfigProperty> collect = configProperties
                .stream().filter(cp -> cp.getKey().equals(key) && cp.getValue().equals(value))
                .collect(Collectors.toList());
        System.out.println(collect);

    }

}
