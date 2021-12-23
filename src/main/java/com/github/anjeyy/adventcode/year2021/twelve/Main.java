package com.github.anjeyy.adventcode.year2021.twelve;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.List;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        List<String> rawRoutes = AdventFileReader.readInputAsStringList("2021/12_caves-map.txt");
        Graph graph = Graph.from(rawRoutes);

        System.out.println("Part I: " + graph.determineDistinctPaths());
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawRoutes = AdventFileReader.readInputAsStringList("2021/12_caves-map.txt");
        Graph graph = Graph.from(rawRoutes);

        System.out.println("Part II: " + graph.determineAdvancedDistinctPaths());
    }
}
