package com.github.anjeyy.adventcode.year2021.eight;

class Output {

    private static final int ONE_LENGTH = 2;
    private static final int FOUR_LENGTH = 4;
    private static final int SEVEN_LENGTH = 3;
    private static final int EIGHT_LENGTH = 7;

    private final String encoded;

    Output(String encoded) {
        this.encoded = encoded;
    }

    static Output of(String encoded) {
        return new Output(encoded);
    }

    boolean isUniqueDigit() {
        return encoded.length() == ONE_LENGTH ||
            encoded.length() == FOUR_LENGTH ||
            encoded.length() == SEVEN_LENGTH ||
            encoded.length() == EIGHT_LENGTH;
    }
}
