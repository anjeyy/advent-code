package com.github.anjeyy.adventcode.year2021.nine;

import com.github.anjeyy.adventcode.CollectionUtils;
import com.github.anjeyy.adventcode.Constants;

import java.util.*;
import java.util.stream.Collectors;

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

    List<Integer> threeLargestBasins() {
        List<List<Integer>> result = new ArrayList<>();
        List<Map.Entry<Integer, Integer>> lowPointIndices = determineLowPointIndices();

        for (Map.Entry<Integer, Integer> lowPointIndex : lowPointIndices) {
            Queue<Map.Entry<Integer, Integer>> flowPoints = new ArrayDeque<>();
            flowPoints.offer(lowPointIndex);
            List<Integer> pathResult = new ArrayList<>();
            while (CollectionUtils.isNotEmpty(flowPoints)) {
                Map.Entry<Integer, Integer> currentFlowPoint = flowPoints.poll();
                pathResult.add(map[currentFlowPoint.getKey()][currentFlowPoint.getValue()]);
                //todo debug
                addLeftNeighbour(currentFlowPoint, flowPoints);
                addRightNeighbour(currentFlowPoint, flowPoints);
                addBelowNeighbour(currentFlowPoint, flowPoints);
                addAboveNeighbour(currentFlowPoint, flowPoints);
                map[currentFlowPoint.getKey()][currentFlowPoint.getValue()] = 9;
            }
            result.add(pathResult);
        }

        while (result.size() > 3) {
            int lowestBasin = Integer.MAX_VALUE;
            int index = -1;
            for (List<Integer> currentBasin : result) {
                if (currentBasin.size() < lowestBasin) {
                    lowestBasin = currentBasin.size();
                    index = result.indexOf(currentBasin);
                }
            }
            result.remove(index);
        }
        return result.stream().map(List::size).collect(Collectors.toList());
    }

    private void addLeftNeighbour(Map.Entry<Integer, Integer> currentFlowPoint, Queue<Map.Entry<Integer, Integer>> flowPoints) {
        int column = currentFlowPoint.getValue();
        boolean leftNeighbourInRange = column - 1 >= 0;
        if (leftNeighbourInRange) {
            Map.Entry<Integer, Integer> leftNeighbour = new AbstractMap.SimpleEntry<>(currentFlowPoint.getKey(), currentFlowPoint.getValue() - 1);
            if (isNotNine(leftNeighbour)) {
                flowPoints.offer(leftNeighbour);
            }
        }
    }

    private void addRightNeighbour(Map.Entry<Integer, Integer> currentFlowPoint, Queue<Map.Entry<Integer, Integer>> flowPoints) {
        int row = currentFlowPoint.getKey();
        int column = currentFlowPoint.getValue();
        boolean rightNeighbourInRange = column + 1 < map[row].length;
        if (rightNeighbourInRange) {
            Map.Entry<Integer, Integer> rightNeighbour = new AbstractMap.SimpleEntry<>(currentFlowPoint.getKey(), currentFlowPoint.getValue() + 1);
            if (isNotNine(rightNeighbour)) {
                flowPoints.offer(rightNeighbour);
            }
        }
    }

    private void addBelowNeighbour(Map.Entry<Integer, Integer> currentFlowPoint, Queue<Map.Entry<Integer, Integer>> flowPoints) {
        int row = currentFlowPoint.getKey();
        boolean belowNeighbourInRange = row + 1 < map.length;
        if (belowNeighbourInRange) {
            Map.Entry<Integer, Integer> belowNeighbour = new AbstractMap.SimpleEntry<>(currentFlowPoint.getKey() + 1, currentFlowPoint.getValue());
            if (isNotNine(belowNeighbour)) {
                flowPoints.offer(belowNeighbour);
            }
        }
    }

    private void addAboveNeighbour(Map.Entry<Integer, Integer> currentFlowPoint, Queue<Map.Entry<Integer, Integer>> flowPoints) {
        int row = currentFlowPoint.getKey();
        boolean aboveNeighbourInRange = row - 1 >= 0;
        if (aboveNeighbourInRange) {
            Map.Entry<Integer, Integer> aboveNeighbour = new AbstractMap.SimpleEntry<>(currentFlowPoint.getKey() - 1, currentFlowPoint.getValue());
            if (isNotNine(aboveNeighbour)) {
                flowPoints.offer(aboveNeighbour);
            }
        }
    }

    private boolean isNotNine(Map.Entry<Integer, Integer> currentFlowPoint) {
        return map[currentFlowPoint.getKey()][currentFlowPoint.getValue()] != 9;
    }

    List<Integer> determineLowPoints() {
        List<Map.Entry<Integer, Integer>> lowPointIndices = determineLowPointIndices();
        return lowPointIndices.stream().map(e -> map[e.getKey()][e.getValue()] + 1).collect(Collectors.toList());
    }

    private List<Map.Entry<Integer, Integer>> determineLowPointIndices() {
        List<Map.Entry<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (isLowPoint(i, j)) {
                    Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(i, j);
                    result.add(entry);
                }
            }
        }
        return result;
    }

    private boolean isLowPoint(int row, int column) {
        return leftIsHigher(row, column) && belowIsHigher(row, column) && rightIsHigher(row, column) && aboveIsHigher(row, column);
    }

    private boolean leftIsHigher(Map.Entry<Integer, Integer> entry) {
        return leftIsHigher(entry.getKey(), entry.getValue());
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
