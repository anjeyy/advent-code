package com.github.anjeyy.adventcode.year2021.five;

class VentRange {

    private final VentCoordinate start;
    private final VentCoordinate end;

    private VentRange(VentCoordinate start, VentCoordinate end) {
        this.start = start;
        this.end = end;
        validateRange();
    }

    private VentRange(String rawRange) {
        this.start = extractStart(rawRange);
        this.end = extractEnd(rawRange);
        validateRange();
    }

    private static VentCoordinate extractStart(String rawRange) {
        String rawStart = rawRange.split(" -> ")[0];
        return VentCoordinate.from(rawStart);
    }

    private static VentCoordinate extractEnd(String rawRange) {
        String rawEnd = rawRange.split(" -> ")[1];
        return VentCoordinate.from(rawEnd);
    }

    private void validateRange() {
        if (
            isNotHorizontalMovement() &&
                isNotVerticalMovement() &&
                isNotDiagonalMovement()
        ) {
            throw new IllegalArgumentException("Condition NOT met.");
        }
    }

    static VentRange with(VentCoordinate start, VentCoordinate end) {
        return new VentRange(start, end);
    }

    static VentRange with(String rawRange) {
        return new VentRange(rawRange);
    }

    VentCoordinate getStart() {
        return start;
    }

    VentCoordinate getEnd() {
        return end;
    }

    boolean isNotHorizontalMovement() {
        return !isHorizontalMovement();
    }

    boolean isHorizontalMovement() {
        int y1 = start.getY();
        int y2 = end.getY();
        return y1 == y2;
    }

    boolean isNotVerticalMovement() {
        return !isVerticalMovement();
    }

    boolean isVerticalMovement() {
        int x1 = start.getX();
        int x2 = end.getX();
        return x1 == x2;
    }

    boolean isNotDiagonalMovement() {
        return !isDiagonalMovement();
    }

    boolean isDiagonalMovement() {
        return isDiagonalSymmetricMovement() || isDiagonalAsymmetricMovement() || isDiagonalDiffSymmetricMovement();
    }

    boolean isNotDiagonalSymmetricMovement() {
        return !isDiagonalSymmetricMovement();
    }

    boolean isDiagonalSymmetricMovement() {
        int x1 = start.getX();
        int x2 = end.getX();
        int y1 = start.getY();
        int y2 = end.getY();
        return x1 == y1 && x2 == y2; // 1,1 -> 3,3
    }

    boolean isNotDiagonalAsymmetricMovement() {
        return !isDiagonalAsymmetricMovement();
    }

    boolean isDiagonalAsymmetricMovement() {
        int x1 = start.getX();
        int x2 = end.getX();
        int y1 = start.getY();
        int y2 = end.getY();

        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        return xDiff == (yDiff * -1);    // 9,7 -> 7,9
    }

    boolean isNotDiagonalDiffSymmetricMovement() {
        return !isDiagonalDiffSymmetricMovement();
    }

    boolean isDiagonalDiffSymmetricMovement() {
        int x1 = start.getX();
        int x2 = end.getX();
        int y1 = start.getY();
        int y2 = end.getY();

        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        return xDiff == yDiff;    // 2,0 -> 6,4 | 6,4 - 2,0 = 4,4 -> diagonal
    }

    @Override
    public String toString() {
        return "VentRange{" + start + " -> " + end + '}';
    }
}
