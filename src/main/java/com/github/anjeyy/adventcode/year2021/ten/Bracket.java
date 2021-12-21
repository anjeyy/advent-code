package com.github.anjeyy.adventcode.year2021.ten;

import java.util.Arrays;
import java.util.NoSuchElementException;

enum Bracket {
    ROUND_BRACKET('(', ')') {
        @Override
        long score() {
            return 3;
        }
    },
    SQUARE_BRACKET('[', ']') {
        @Override
        long score() {
            return 57;
        }
    },
    BRACE_BRACKET('{', '}') {
        @Override
        long score() {
            return 1197;
        }
    },
    ANGLE_BRACKET('<', '>') {
        @Override
        long score() {
            return 25137;
        }
    };

    private final char opening;
    private final char closing;

    Bracket(char opening, char closing) {
        this.opening = opening;
        this.closing = closing;
    }

    static long score(char anyChar) {
        Bracket bracket = from(anyChar);
        return bracket.score();
    }

    abstract long score();

    static Bracket from(char anyChar) {
        return Arrays.stream(values())
                     .filter(bracket -> anyChar == bracket.opening || anyChar == bracket.closing)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("No bracket possible for: " + anyChar));
    }

    static boolean isNotOpening(char anyChar) {
        return !isOpening(anyChar);
    }

    static boolean isOpening(char anyChar) {
        return Arrays.stream(values()).map(e -> e.opening).anyMatch(c -> c == anyChar);
    }

    static boolean isNotClosing(char anyChar) {
        return !isClosing(anyChar);
    }

    static boolean isClosing(char anyChar) {
        return Arrays.stream(values()).map(e -> e.closing).anyMatch(c -> c == anyChar);
    }

}
