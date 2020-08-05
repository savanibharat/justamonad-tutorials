package com.justamonad.tutorials.properties;

public class ConfigProperty {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ConfigProperty withKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConfigProperty withValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "ConfigProperty{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}