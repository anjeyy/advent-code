package com.github.anjeyy.adventcode.year2021.five;

class VentCoordinate {

    private final int x;
    private final int y;

    private VentCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private VentCoordinate(String rawCoordinate) {
        this.x = extractVertical(rawCoordinate);
        this.y = extractHorizontal(rawCoordinate);
    }

    private int extractVertical(String rawCoordinate) {
        String rawValue = rawCoordinate.split(",")[0];
        return Integer.parseInt(rawValue);
    }

    private int extractHorizontal(String rawCoordinate) {
        String rawValue = rawCoordinate.split(",")[1];
        return Integer.parseInt(rawValue);
    }

    static VentCoordinate from(int x, int y) {
        return new VentCoordinate(x, y);
    }

    static VentCoordinate from(String rawCoordinate) {
        return new VentCoordinate(rawCoordinate);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
