package com.github.anjeyy.adventcode.year2021.eleven;

import com.github.anjeyy.adventcode.CollectionUtils;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map.Entry;

class EnergyGrid {

    private static final int ROW_SIZE = 10;
    private static final int COLUMN_SIZE = 10;
    private final Deque<Entry<Integer, Integer>> stack = new ArrayDeque<>();

    private final int[][] grid;

    EnergyGrid(List<String> rawLevel) {
        this.grid = extractGrid(rawLevel);
    }

    private static int[][] extractGrid(List<String> rawLevel) {
        int[][] result = new int[ROW_SIZE][COLUMN_SIZE];
        for (int i = 0; i < rawLevel.size(); i++) {
            String row = rawLevel.get(i);
            char[] rawRow = row.toCharArray();
            for (int j = 0; j < rawRow.length; j++) {
                String currentValue = String.valueOf(rawRow[j]);
                int value = Integer.parseInt(currentValue);
                result[i][j] = value;
            }
        }
        return result;
    }

    long firstSynchronizedFlash() {
        boolean allFlashed = false;
        long counter = 1L;
        while (!allFlashed) {
            increase();
            long flashes = countFlashes();
            allFlashed = (flashes == ROW_SIZE * COLUMN_SIZE);
            if (allFlashed) {
                return counter;
            }
            counter++;
        }
        throw new IllegalStateException("Error.");
    }

    long calculateFlashes() {
        long result = 0L;
        for (int i = 0; i < 100; i++) {
            increase();
            result += countFlashes();
        }
        return result;
    }

    private void increase() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j]++;
                addToNeighbourStack(i, j);
            }
        }
        increaseNeighbours();
    }

    private void increaseNeighbours() {
        while (CollectionUtils.isNotEmpty(stack)) {
            //todo debug for missing increase!
            Entry<Integer, Integer> entry = stack.pop();
            grid[entry.getKey()][entry.getValue()] = grid[entry.getKey()][entry.getValue()] * -1;
            addLeftNeighbour(entry);
            addRightNeighbour(entry);
            addAboveNeighbour(entry);
            addBelowNeighbour(entry);
            addLeftTopNeighbour(entry);
            addLeftBottomNeighbour(entry);
            addRightTopNeighbour(entry);
            addRightBottomNeighbour(entry);
        }
        resetMarkedEntries();
    }

    private void addLeftNeighbour(Entry<Integer, Integer> entry) {
        int leftNeighbour = entry.getValue() - 1;
        if (leftNeighbour >= 0 && grid[entry.getKey()][leftNeighbour] >= 0) {
            grid[entry.getKey()][leftNeighbour]++;
            addToNeighbourStack(entry.getKey(), leftNeighbour);
        }
    }

    private void addRightNeighbour(Entry<Integer, Integer> entry) {
        int rightNeighbour = entry.getValue() + 1;
        if (rightNeighbour < COLUMN_SIZE && grid[entry.getKey()][rightNeighbour] >= 0) {
            grid[entry.getKey()][rightNeighbour]++;
            addToNeighbourStack(entry.getKey(), rightNeighbour);
        }
    }

    private void addAboveNeighbour(Entry<Integer, Integer> entry) {
        int aboveNeighbour = entry.getKey() - 1;
        if (aboveNeighbour >= 0 && grid[aboveNeighbour][entry.getValue()] >= 0) {
            grid[aboveNeighbour][entry.getValue()]++;
            addToNeighbourStack(aboveNeighbour, entry.getValue());
        }
    }

    private void addBelowNeighbour(Entry<Integer, Integer> entry) {
        int belowNeighbour = entry.getKey() + 1;
        if (belowNeighbour < ROW_SIZE && grid[belowNeighbour][entry.getValue()] >= 0) {
            grid[belowNeighbour][entry.getValue()]++;
            addToNeighbourStack(belowNeighbour, entry.getValue());
        }
    }

    private void addLeftTopNeighbour(Entry<Integer, Integer> entry) {
        int row = entry.getKey() - 1;
        int column = entry.getValue() - 1;
        if (row >= 0 && column >= 0 && grid[row][column] >= 0) {
            grid[row][column]++;
            addToNeighbourStack(row, column);
        }
    }

    private void addLeftBottomNeighbour(Entry<Integer, Integer> entry) {
        int row = entry.getKey() + 1;
        int column = entry.getValue() - 1;
        if (row < ROW_SIZE && column >= 0 && grid[row][column] >= 0) {
            grid[row][column]++;
            addToNeighbourStack(row, column);
        }
    }

    private void addRightTopNeighbour(Entry<Integer, Integer> entry) {
        int row = entry.getKey() - 1;
        int column = entry.getValue() + 1;
        if (row >= 0 && column < COLUMN_SIZE && grid[row][column] >= 0) {
            grid[row][column]++;
            addToNeighbourStack(row, column);
        }
    }

    private void addRightBottomNeighbour(Entry<Integer, Integer> entry) {
        int row = entry.getKey() + 1;
        int column = entry.getValue() + 1;
        if (row < ROW_SIZE && column < COLUMN_SIZE && grid[row][column] >= 0) {
            grid[row][column]++;
            addToNeighbourStack(row, column);
        }
    }

    private void addToNeighbourStack(int i, int j) {
        Entry<Integer, Integer> entry = new SimpleEntry<>(i, j);
        boolean isNotAlreadyStacked = !stack.contains(entry);
        if (grid[i][j] > 9 && isNotAlreadyStacked) {
            stack.push(entry);
        }
    }

    private void resetMarkedEntries() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    grid[i][j] = grid[i][j] * -1;
                }
            }
        }
    }

    private long countFlashes() {
        long result = 0L;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 9) {
                    result++;
                    grid[i][j] = 0;
                }
            }
        }
        return result;
    }

}
