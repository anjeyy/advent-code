package com.github.anjeyy.adventcode.year2021.one;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        List<Integer> rawSeaMeasurements = AdventFileReader
            .readInputAsStringList("2021/01_sea-floor-measurement.txt")
            .stream()
            .filter(AdventFileReader.stringIsNotBlank())
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        SeaFloorMeasurement seaFloorMeasurement = SeaFloorMeasurement.from(rawSeaMeasurements);
        int increasedMeasures = seaFloorMeasurement.countIncreasedMeasurements();

        System.out.println("Part I: " + increasedMeasures);
    }

    private static void solvePartTwo() throws IOException {
        List<Integer> rawSeaMeasurements = AdventFileReader
            .readInputAsStringList("2021/01_sea-floor-measurement.txt")
            .stream()
            .filter(AdventFileReader.stringIsNotBlank())
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toList());

        SeaFloorMeasurement seaFloorMeasurement = SeaFloorMeasurement.from(rawSeaMeasurements);
        int increasedMeasures = seaFloorMeasurement.countThreePackagesIncreasedMeasurements();

        System.out.println("Part II: " + increasedMeasures);
    }
}
