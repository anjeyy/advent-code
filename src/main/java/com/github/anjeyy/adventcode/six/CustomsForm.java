package com.github.anjeyy.adventcode.six;

import java.util.Objects;

class CustomsForm {

    private final String rawCustoms;

    private CustomsForm(String rawCustoms) {
        Objects.requireNonNull(rawCustoms, "Provide a customs form result.");
        this.rawCustoms = rawCustoms;
    }

    public String getRawCustoms() {
        return rawCustoms;
    }
}
