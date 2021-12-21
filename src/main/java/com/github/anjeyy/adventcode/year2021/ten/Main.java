package com.github.anjeyy.adventcode.year2021.ten;

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
        ChunkHandler chunkHandler =
            AdventFileReader.readInputAsStringList("2021/10_navigation-subsystem.txt")
                            .stream()
                            .collect(Collectors.collectingAndThen(Collectors.toList(), ChunkHandler::from));

        System.out.println("Part I: " + chunkHandler.calculateCorruptedScore());
    }

    private static void solvePartTwo() throws IOException {
        ChunkHandler chunkHandler =
            AdventFileReader.readInputAsStringList("2021/10_navigation-subsystem.txt")
                            .stream()
                            .collect(Collectors.collectingAndThen(Collectors.toList(), ChunkHandler::from));

        System.out.println("Part II: " + chunkHandler.calculateIncompleteScore());
    }
}
