package com.justamonad.tutorials.object.mapping.invoice.request;

public class Tax {
    private String name;
    private String percent;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPercent() {
        return percent;
    }
    public void setPercent(String percent) {
        this.percent = percent;
    }
}

