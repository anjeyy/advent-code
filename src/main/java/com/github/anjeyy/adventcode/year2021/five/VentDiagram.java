package com.github.anjeyy.adventcode.year2021.five;

import java.util.List;
import java.util.function.Function;

class VentDiagram {

    private final int[][] board;

    public VentDiagram(List<VentRange> ventRanges) {
        int maxRowLength = determineMaxRowLength(ventRanges) + 1;
        int maxColumnLength = determineMaxColumnLength(ventRanges) + 1;
        this.board = new int[maxRowLength][maxColumnLength];
    }

    private static int determineMaxColumnLength(List<VentRange> ventRanges) {
        int maxStartRowLength = determineMaxRowLengthWith(ventRanges, VentRange::getStart);
        int maxEndRowLength = determineMaxRowLengthWith(ventRanges, VentRange::getEnd);
        return Integer.max(maxStartRowLength, maxEndRowLength);
    }

    private static int determineMaxRowLengthWith(
        List<VentRange> ventRanges,
        Function<VentRange, VentCoordinate> rangeToCoordinate
    ) {
        return ventRanges.stream()
                         .map(rangeToCoordinate)
                         .map(VentCoordinate::getX)
                         .max(Integer::compareTo)
                         .orElseThrow();
    }

    private static int determineMaxRowLength(List<VentRange> ventRanges) {
        int maxStartRowLength = determineMaxColumnLengthWith(ventRanges, VentRange::getStart);
        int maxEndRowLength = determineMaxColumnLengthWith(ventRanges, VentRange::getEnd);
        return Integer.max(maxStartRowLength, maxEndRowLength);
    }

    private static int determineMaxColumnLengthWith(
        List<VentRange> ventRanges,
        Function<VentRange, VentCoordinate> rangeToCoordinate
    ) {
        return ventRanges.stream()
                         .map(rangeToCoordinate)
                         .map(VentCoordinate::getY)
                         .max(Integer::compareTo)
                         .orElseThrow();
    }


    void update(List<VentRange> ventRanges) {
        for (VentRange currentRange : ventRanges) {
            updateForSingleRange(currentRange);
        }
    }

    void updateDiagonal(List<VentRange> ventRanges) {
        for (VentRange currentRange : ventRanges) {
            if (currentRange.isHorizontalMovement() || currentRange.isVerticalMovement()) {
                updateForSingleRange(currentRange);
            }
            if (currentRange.isDiagonalMovement()) {
                updateForSingleRangeDiagonally(currentRange);
            }
        }
    }

    private void updateForSingleRange(VentRange ventRange) {
        VentCoordinate start = ventRange.getStart();
        VentCoordinate end = ventRange.getEnd();

        int x1;
        int x2;
        if (start.getX() <= end.getX()) {
            x1 = start.getX();
            x2 = end.getX();
        } else {
            x1 = end.getX();
            x2 = start.getX();
        }

        int y1;
        int y2;
        if (start.getY() <= end.getY()) {
            y1 = start.getY();
            y2 = end.getY();
        } else {
            y1 = end.getY();
            y2 = start.getY();
        }

        if (ventRange.isHorizontalMovement()) {
            while (x1 != x2 + 1) {
                board[y1][x1]++;
                x1++;
            }
        } else if (ventRange.isVerticalMovement()) {
            while (y1 != y2 + 1) {
                board[y1][x1]++;
                y1++;
            }
        }
    }

    private void updateForSingleRangeDiagonally(VentRange ventRange) {
        VentCoordinate start = ventRange.getStart();
        VentCoordinate end = ventRange.getEnd();

        int x1;
        int y1;
        int x2;
        int y2;
        if (start.getX() <= end.getX()) {
            x1 = start.getX();
            y1 = start.getY();
            x2 = end.getX();
            y2 = end.getY();
        } else {
            x1 = end.getX();
            y1 = end.getY();
            x2 = start.getX();
            y2 = start.getY();
        }

        if (ventRange.isDiagonalSymmetricMovement() || ventRange.isDiagonalDiffSymmetricMovement()) {
            while (x1 != (x2 + 1) && y1 != (y2 + 1)) {
                board[y1][x1]++;
                x1++;
                y1++;
            }
        } else if (ventRange.isDiagonalAsymmetricMovement()) {
            while (x1 != x2 + 1) {
                board[y1][x1]++;
                x1++;
                y1--;
            }
        }
    }

    int countDangerousArea() {
        int result = 0;
        for (int[] currentRow : board) {
            for (int value : currentRow) {
                if (value > 1) {
                    result++;
                }
            }
        }
        return result;
    }

}
