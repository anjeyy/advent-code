package com.github.anjeyy.adventcode.year2021.eleven;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
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
        EnergyGrid energyGrid =
            AdventFileReader.readInputAsStringList("2021/11_octopus-energy-level.txt")
                            .stream()
                            .collect(Collectors.collectingAndThen(Collectors.toList(), EnergyGrid::new));

        System.out.println("Part I: " + energyGrid.calculateFlashes());
    }

    private static void solvePartTwo() throws IOException {
        EnergyGrid energyGrid =
            AdventFileReader.readInputAsStringList("2021/11_octopus-energy-level.txt")
                            .stream()
                            .collect(Collectors.collectingAndThen(Collectors.toList(), EnergyGrid::new));

        System.out.println("Part II: " + energyGrid.firstSynchronizedFlash());
    }
}
