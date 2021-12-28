package com.github.anjeyy.adventcode.year2021.three;

enum Bit {

    _0('0'),
    _1('1');

    private final char value;

    Bit(char value) {
        this.value = value;
    }

    char getAsChar() {
        return value;
    }

    int getAsInt() {
        String stringValue = getAsString();
        return Integer.parseInt(stringValue);
    }

    String getAsString() {
        return String.valueOf(value);
    }

    static boolean isInvalid(char c) {
        return !isValid(c);
    }

    static boolean isValid(char c) {
        return isZero(c) || isOne(c);
    }

    static boolean isZero(char c) {
        return !isNotZero(c);
    }

    static boolean isNotZero(char c) {
        return c != Bit._0.getAsChar();
    }

    static boolean isOne(char c) {
        return !isNotOne(c);
    }

    static boolean isNotOne(char c) {
        return c != Bit._1.getAsChar();
    }

    static Bit negate(char c) {
        if (c == _0.value) {
            return _1;
        } else if (c == _1.value) {
            return _0;
        }
        throw new IllegalArgumentException(
                String.format("Can not negate bit for non-bit value '%s'", c)
        );
    }
}
