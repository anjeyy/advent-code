package com.github.anjeyy.adventcode.year2020.five;

import java.util.regex.Pattern;

class SeatParser {

    private static final Pattern ENCRYPTION_PATTERN = Pattern.compile("[F,B]{7}[L,R]{3}");

    private final String encodedSeat;
    private final BinarySearch rowSearch;
    private final BinarySearch columnSearch;

    static SeatParser from(String rawEncodedSeat) {
        return new SeatParser(rawEncodedSeat);
    }

    private SeatParser(String rawEncodedSeat) {
        this.encodedSeat = rawEncodedSeat;
        this.rowSearch =
            new BinarySearch.BinarySearchBuilder()
                .minRange(0)
                .maxRange(127)
                .lowerHalf(SeatSpecification.FRONT)
                .upperHalf(SeatSpecification.BACK)
                .build();
        this.columnSearch =
            new BinarySearch.BinarySearchBuilder()
                .minRange(0)
                .maxRange(7)
                .lowerHalf(SeatSpecification.LEFT)
                .upperHalf(SeatSpecification.RIGHT)
                .build();
        checkForValidEncryption();
    }

    private void checkForValidEncryption() {
        boolean isMatching = ENCRYPTION_PATTERN.matcher(encodedSeat).matches();
        if (!isMatching) {
            throw new IllegalArgumentException("Please provide a valid seat encryption.");
        }
    }

    int determineRow() {
        String rowString = encodedSeat.substring(0, 7);
        return rowSearch.findNumber(rowString);
    }

    int determineColumn() {
        String columnString = encodedSeat.substring(7, 10);
        return columnSearch.findNumber(columnString);
    }
}
