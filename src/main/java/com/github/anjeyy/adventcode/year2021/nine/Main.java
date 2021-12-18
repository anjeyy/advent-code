package com.github.anjeyy.adventcode.year2021.nine;

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
        SmokeHeightMap smokeHeightMap = AdventFileReader.readInputAsStringList("2021/09_smoke-heightmap.txt")
                                                        .stream()
                                                        .collect(Collectors.collectingAndThen(Collectors.toList(),
                                                                                              SmokeHeightMap::new));

        System.out.println("Part I: " + smokeHeightMap.determineLowPoints().stream().mapToLong(s -> s).sum());
    }

    private static void solvePartTwo() throws IOException {
        SmokeHeightMap smokeHeightMap = AdventFileReader.readInputAsStringList("2021/09_smoke-heightmap.txt")
                                                        .stream()
                                                        .collect(Collectors.collectingAndThen(Collectors.toList(),
                                                                                              SmokeHeightMap::new));

        System.out.println("Part II: " + smokeHeightMap.threeLargestBasins().stream().reduce((a, b) -> a * b));
    }
}
