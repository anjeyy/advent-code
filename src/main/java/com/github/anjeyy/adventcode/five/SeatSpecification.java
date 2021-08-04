package com.github.anjeyy.adventcode.five;

import java.util.Arrays;

enum SeatSpecification {
    FRONT('F'),
    BACK('B'),
    LEFT('L'),
    RIGHT('R');

    private final Character abbreviation;

    SeatSpecification(Character abbreviation) {
        this.abbreviation = abbreviation;
    }

    static SeatSpecification fromAbbreviation(Character abbr) {
        return Arrays.stream(values()).filter(a -> a.abbreviation.equals(abbr)).findAny().orElseThrow();
    }
}
