package com.github.anjeyy.adventcode.year2021.one;

import java.util.ArrayList;
import java.util.List;

class SeaFloorMeasurement {

    private final List<Integer> seaFloorMeasurements;

    private SeaFloorMeasurement(List<Integer> seaFloorMeasurements) {
        this.seaFloorMeasurements = seaFloorMeasurements;
    }

    static SeaFloorMeasurement from(List<Integer> seaFloorMeasurements) {
        return new SeaFloorMeasurement(seaFloorMeasurements);
    }

    int countIncreasedMeasurements() {
        return countIncreasedMeasurements(seaFloorMeasurements);
    }

    int countThreePackagesIncreasedMeasurements() {
        int result = 0;
        if (seaFloorMeasurements.isEmpty()) {
            return result;
        }

        List<Integer> threePackages = new ArrayList<>();
        for (int i = 0; i <= seaFloorMeasurements.size() - 3; i++) {
            Integer first = seaFloorMeasurements.get(i);
            Integer second = seaFloorMeasurements.get(i + 1);
            Integer third = seaFloorMeasurements.get(i + 2);
            Integer sum = first + second + third;
            threePackages.add(sum);
        }
        return countIncreasedMeasurements(threePackages);
    }

    private static int countIncreasedMeasurements(List<Integer> seaFloorMeasurements) {
        int result = 0;
        if (seaFloorMeasurements.isEmpty()) {
            return result;
        }

        int currentMeasure = seaFloorMeasurements.get(0);
        for (int i = 1; i < seaFloorMeasurements.size(); i++) {
            int newMeasure = seaFloorMeasurements.get(i);
            if (newMeasure > currentMeasure) {
                result++;
            }
            currentMeasure = newMeasure;
        }
        return result;
    }
}
