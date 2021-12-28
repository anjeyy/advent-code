package com.github.anjeyy.adventcode.year2021.thirteen;

import com.github.anjeyy.adventcode.Constants;
import java.util.Arrays;

class FoldInstruction {

    private final Kind kind;
    private final int value;

    private FoldInstruction(String raw) {
        this.kind = extractKind(raw);
        this.value = extractValue(raw);
    }

    private static Kind extractKind(String raw) {
        String[] relevant = splitLastPart(raw);
        String rawKind = relevant[0];
        return Kind.of(rawKind);
    }

    private static int extractValue(String raw) {
        String[] relevant = splitLastPart(raw);
        String rawValue = relevant[1];
        return Integer.parseInt(rawValue);
    }

    private static String[] splitLastPart(String raw) {
        String[] whitespaceSplit = raw.split(Constants.WHITESPACE);
        return whitespaceSplit[2].split("=");
    }

    static FoldInstruction of(String raw) {
        return new FoldInstruction(raw);
    }

     Kind getKind() {
        return kind;
    }

     int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "FoldInstruction{" +
            "kind=" + kind +
            ", value=" + value +
            '}';
    }

    enum Kind {
        X("x"),
        Y("y");

        private final String value;

        Kind(String value) {
            this.value = value;
        }

        static Kind of(String value) {
            return Arrays.stream(values()).filter(k -> k.value.equals(value)).findFirst().orElseThrow();
        }
    }
}
