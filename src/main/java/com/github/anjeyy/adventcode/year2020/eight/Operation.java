package com.github.anjeyy.adventcode.year2020.eight;

import java.util.Arrays;

enum Operation {
    ACCUMULATOR("acc"),
    JUMPS("jmp"),
    NO_OPERATION("nop");

    private final String abbreviation;

    Operation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    static Operation from(String string) {
        return Arrays.stream(values()).filter(s -> s.abbreviation.equals(string)).findAny().orElseThrow();
    }
}
