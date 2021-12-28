package com.github.anjeyy.adventcode.year2021.thirteen;

class Coordinate {

    private final int x;
    private final int y;

    private Coordinate(String raw) {
        this.x = extractRow(raw);
        this.y = extractColumn(raw);
    }

    private int extractRow(String raw) {
        String[] split = raw.split(",");
        String rawRow = split[0];
        return Integer.parseInt(rawRow);
    }

    private int extractColumn(String raw) {
        String[] split = raw.split(",");
        String rawRow = split[1];
        return Integer.parseInt(rawRow);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Coordinate from(String raw) {
        return new Coordinate(raw);
    }

    static Coordinate from(int x, int y) {
        return new Coordinate(x, y);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
