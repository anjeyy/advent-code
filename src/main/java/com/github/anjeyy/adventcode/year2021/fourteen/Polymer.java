package com.github.anjeyy.adventcode.year2021.fourteen;

class Polymer {

    private String value;

    private Polymer(String value) {
        this.value = value;
    }

    static Polymer of(String value) {
        return new Polymer(value);
    }

    String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Polymer{" +
            "value='" + value + '\'' +
            '}';
    }
}
