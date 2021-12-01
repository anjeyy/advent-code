package com.github.anjeyy.adventcode.year2021.one;

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
