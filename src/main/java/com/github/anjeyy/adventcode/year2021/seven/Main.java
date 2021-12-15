package com.github.anjeyy.adventcode.year2021.seven;

import com.github.anjeyy.adventcode.AdventFileReader;

import java.io.IOException;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        String rawHorizontalCrabs = AdventFileReader
                .readInputAsString("2021/seven_horizontal-crabs.txt");
        CrabFuelAnalyzer crabFuelAnalyzer = new CrabFuelAnalyzer(rawHorizontalCrabs);

        System.out.println("Part I: " + crabFuelAnalyzer.horizontalPositionWithLeastFuel());
    }

    private static void solvePartTwo() throws IOException {
        String rawHorizontalCrabs = AdventFileReader
                .readInputAsString("2021/seven_horizontal-crabs.txt");
        CrabFuelAnalyzer crabFuelAnalyzer = new CrabFuelAnalyzer(rawHorizontalCrabs);

        System.out.println("Part II: " + crabFuelAnalyzer.horizontalPositionWithComplexLeastFuel());
    }
}
