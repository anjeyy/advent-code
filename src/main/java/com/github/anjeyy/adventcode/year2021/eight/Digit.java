package com.github.anjeyy.adventcode.year2021.eight;

import java.util.Optional;

enum Digit {
    _0("0", 6),
    _1("1", 2),
    _2("2", 5),
    _3("3", 5),
    _4("4", 4),
    _5("5", 5),
    _6("6", 6),
    _7("7", 3),
    _8("8", 7),
    _9("9", 6);

    private final String value;
    private final int length;

    Digit(String value, int length) {
        this.value = value;
        this.length = length;
    }

    int getLength() {
        return length;
    }

    String asString() {
        return value;
    }

    static Optional<Digit> encodeViaUniqueLength(int signalLength) {
        if (signalLength == Digit._1.getLength()) {
            return Optional.of(Digit._1);
        } else if (signalLength == Digit._4.getLength()) {
            return Optional.of(Digit._4);
        } else if (signalLength == Digit._7.getLength()) {
            return Optional.of(Digit._7);
        } else if (signalLength == Digit._8.getLength()) {
            return Optional.of(Digit._8);
        }
        return Optional.empty();
    }
}
