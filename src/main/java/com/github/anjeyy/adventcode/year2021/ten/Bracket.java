package com.github.anjeyy.adventcode.year2021.ten;

import java.util.Arrays;
import java.util.NoSuchElementException;

enum Bracket {
    ROUND_BRACKET('(', ')') {
        @Override
        protected long score() {
            return 3;
        }

        @Override
        protected long closingScore() {
            return 1;
        }
    },
    SQUARE_BRACKET('[', ']') {
        @Override
        protected long score() {
            return 57;
        }

        @Override
        protected long closingScore() {
            return 2;
        }
    },
    BRACE_BRACKET('{', '}') {
        @Override
        protected long score() {
            return 1197;
        }

        @Override
        protected long closingScore() {
            return 3;
        }
    },
    ANGLE_BRACKET('<', '>') {
        @Override
        protected long score() {
            return 25137;
        }

        @Override
        protected long closingScore() {
            return 4;
        }
    };

    private final char opening;
    private final char closing;

    Bracket(char opening, char closing) {
        this.opening = opening;
        this.closing = closing;
    }

    protected abstract long closingScore();

    static long score(char anyChar) {
        Bracket bracket = from(anyChar);
        return bracket.score();
    }

    protected abstract long score();

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
