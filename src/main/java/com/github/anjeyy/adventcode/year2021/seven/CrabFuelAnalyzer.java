package com.github.anjeyy.adventcode.year2021.seven;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        Queue<Integer> alignedPositions = determinePositionRange();
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

    int horizontalPositionWithComplexLeastFuel() {
        Queue<Integer> alignedPositions = determinePositionRange();
        int result = Integer.MAX_VALUE;
        while (!alignedPositions.isEmpty()) {
            int aligned = alignedPositions.poll();
            int fuelCosts = calculateComplexFuelCosts(aligned);
            if (fuelCosts < result) {
                result = fuelCosts;
            }
        }
        return result;
    }

    private int calculateComplexFuelCosts(int alignedPosition) {
        int result = 0;
        for (Integer currentPosition : positions) {
            int cost = currentPosition - alignedPosition;
            cost = normalizeCosts(cost);
            cost = (cost * (cost + 1)) / 2;
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

    private Queue<Integer> determinePositionRange() {
        int maxPosition = Collections.max(positions);
        return IntStream.rangeClosed(0, maxPosition)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
