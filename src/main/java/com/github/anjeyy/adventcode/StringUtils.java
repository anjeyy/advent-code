package com.github.anjeyy.adventcode;

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("No instance allowed.");
    }

    public static boolean isNotBlank(String string) {
        return !string.isBlank();
    }
}
