package com.github.anjeyy.adventcode.year2021.seven;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class CrabFuelAnalyzer {

    private final List<Integer> positions;

    CrabFuelAnalyzer(String rawPositions) {
        this.positions = extractPositions(rawPositions);
    }

    private static List<Integer> extractPositions(String rawPositions) {
        return Arrays.stream(rawPositions.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    int horizontalPositionWithLeastFuel() {
        Queue<Integer> alignedPositions = new ArrayDeque<>(positions);
        int result = Integer.MAX_VALUE;
        while (!alignedPositions.isEmpty()) {
            int aligned = alignedPositions.poll();
            int fuelCosts = calculateFuelCosts(aligned);
            if (fuelCosts < result) {
                result = fuelCosts;
            }
        }
        return result;
    }

    private int calculateFuelCosts(int alignedPosition) {
        int result = 0;
        for (Integer currentPosition : positions) {
            int cost = currentPosition - alignedPosition;
            cost = normalizeCosts(cost);
            result += cost;
        }
        return result;
    }

    private static int normalizeCosts(int cost) {
        if (cost < 0) {
            return cost * (-1);
        }
        return cost;
    }
}
