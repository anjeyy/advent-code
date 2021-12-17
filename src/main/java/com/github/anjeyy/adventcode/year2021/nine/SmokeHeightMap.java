package com.github.anjeyy.adventcode.year2021.nine;

import com.github.anjeyy.adventcode.Constants;

import java.util.ArrayList;
import java.util.List;

class SmokeHeightMap {

    private final int[][] map;

    SmokeHeightMap(List<String> rawMap) {
        this.map = extractCoordinates(rawMap);
    }

    private static int[][] extractCoordinates(List<String> rawMap) {
        int rowSize = rawMap.size();
        int columnSize = rawMap.stream().findFirst().orElseThrow().length();
        int[][] result = new int[rowSize][columnSize];
        for (int i = 0; i < result.length; i++) {
            String rawRow = rawMap.get(i);
            result[i] = extractRow(rawRow);
        }
        return result;
    }

    private static int[] extractRow(String rawRow) {
        String[] rawValues = rawRow.split(Constants.EMPTY);
        int[] result = new int[rawValues.length];
        for (int i = 0; i < rawValues.length; i++) {
            result[i] = Integer.parseInt(rawValues[i]);
        }
        return result;
    }

    List<Integer> determineLowPoints() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isLowPoint(i, j)) {
                    result.add(map[i][j] + 1);
                }
            }
        }
        return result;
    }

    private boolean isLowPoint(int row, int column) {
        return leftIsHigher(row, column) &&
                belowIsHigher(row, column) &&
                rightIsHigher(row, column) &&
                aboveIsHigher(row, column);
    }

    private boolean leftIsHigher(int row, int column) {
        int currentPoint = map[row][column];
        boolean leftNeighbourInRange = column - 1 >= 0;
        if (leftNeighbourInRange) {
            int leftPoint = map[row][column - 1];
            return leftPoint > currentPoint;
        }
        return true;
    }

    private boolean belowIsHigher(int row, int column) {
        int currentPoint = map[row][column];
        boolean belowNeighbourInRange = row + 1 < map.length;
        if (belowNeighbourInRange) {
            int belowPoint = map[row + 1][column];
            return belowPoint > currentPoint;
        }
        return true;
    }

    private boolean rightIsHigher(int row, int column) {
        int currentPoint = map[row][column];
        boolean rightNeighbourInRange = column + 1 < map[row].length;
        if (rightNeighbourInRange) {
            int rightPoint = map[row][column + 1];
            return rightPoint > currentPoint;
        }
        return true;
    }

    private boolean aboveIsHigher(int row, int column) {
        int currentPoint = map[row][column];
        boolean aboveNeighbourInRange = row - 1 >= 0;
        if (aboveNeighbourInRange) {
            int abovePoint = map[row - 1][column];
            return abovePoint > currentPoint;
        }
        return true;
    }
}
