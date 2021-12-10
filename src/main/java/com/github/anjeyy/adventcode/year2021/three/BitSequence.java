package com.github.anjeyy.adventcode.year2021.three;

import java.util.Objects;

class BitSequence {

    private final String bitSequence;

    private BitSequence(String rawBitSequence) {
        this.bitSequence = createValidBitSequence(rawBitSequence);
    }

    private static String createValidBitSequence(String rawBitSequence) {
        Objects.requireNonNull(rawBitSequence, "No rawBitSequence provided.");
        String trimmedBitSequence = rawBitSequence.trim();
        for (char c : trimmedBitSequence.toCharArray()) {
            checkValidBitValue(c);
        }
        return trimmedBitSequence;
    }

    private static void checkValidBitValue(char c) {
        if (Bit.isInvalid(c)) {
            throw new IllegalArgumentException("Invalid bit value: " + c);
        }
    }

    static BitSequence from(String rawBitSequence) {
        return new BitSequence(rawBitSequence);
    }

    long asDecimal() {
        return Long.parseLong(bitSequence, 2);
    }

    BitSequence negate() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bitSequence.length(); i++) {
            char currentBit = bitSequence.charAt(i);
            Bit negatedCurrentBit = Bit.negate(currentBit);
            result.append(negatedCurrentBit.getAsString());
        }
        return new BitSequence(result.toString());
    }

    String getBitSequence() {
        return bitSequence;
    }

    int length() {
        return bitSequence.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSequence that = (BitSequence) o;
        return bitSequence.equals(that.bitSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bitSequence);
    }

    @Override
    public String toString() {
        return "BitSequence{" + bitSequence + "}";
    }
}
