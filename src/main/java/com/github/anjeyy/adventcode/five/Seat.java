package com.github.anjeyy.adventcode.five;

class Seat implements Comparable<Seat> {

    private final int row;
    private final int column;

    private Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    static Seat of(int row, int column) {
        return new Seat(row, column);
    }

    int getSeatId() {
        return row * 8 + column;
    }

    @Override
    public int compareTo(Seat o) {
        if (o == null) return -1;
        return Integer.compare(getSeatId(), o.getSeatId());
    }
}
