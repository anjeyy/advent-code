package com.github.anjeyy.adventcode.five;

class BinarySearch {

    private final int minRange;
    private final int maxRange;
    private final SeatSpecification lowerHalf;
    private final SeatSpecification upperHalf;

    BinarySearch(int minRange, int maxRange, SeatSpecification lowerHalf, SeatSpecification upperHalf) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.lowerHalf = lowerHalf;
        this.upperHalf = upperHalf;
    }

    int findNumber(String rawInput) {
        int lowerLimit = minRange;
        int upperLimit = maxRange;
        for (int i = 0; i < rawInput.length(); i++) {
            Character currentChar = rawInput.charAt(i);
            SeatSpecification currentSpec = SeatSpecification.fromAbbreviation(currentChar);
            int currentInterval = (upperLimit - lowerLimit) + 1;
            int newInterval = currentInterval / 2;
            if (currentSpec == lowerHalf) {
                upperLimit = upperLimit - newInterval;
            } else if (currentSpec == upperHalf) {
                // take upper half
                lowerLimit = lowerLimit + newInterval;
            } else {
                throw new IllegalStateException(
                    String.format(
                        "Seat specification '%s' is NOT part of objects specification '[%s, %s]'",
                        currentSpec,
                        lowerHalf,
                        upperHalf
                    )
                );
            }
        }
        return lowerLimit; // same as upper limit
    }

    static class BinarySearchBuilder {

        private int minRange;
        private int maxRange;
        private SeatSpecification lowerHalf;
        private SeatSpecification upperHalf;

        BinarySearchBuilder minRange(int minRange) {
            this.minRange = minRange;
            return this;
        }

        BinarySearchBuilder maxRange(int maxRange) {
            this.maxRange = maxRange;
            return this;
        }

        BinarySearchBuilder lowerHalf(SeatSpecification lowerHalf) {
            this.lowerHalf = lowerHalf;
            return this;
        }

        BinarySearchBuilder upperHalf(SeatSpecification upperHalf) {
            this.upperHalf = upperHalf;
            return this;
        }

        BinarySearch build() {
            return new BinarySearch(minRange, maxRange, lowerHalf, upperHalf);
        }
    }
}
