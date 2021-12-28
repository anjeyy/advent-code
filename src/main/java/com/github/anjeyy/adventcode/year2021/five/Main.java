package com.github.anjeyy.adventcode.year2021.five;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        List<String> rawVentRanges = AdventFileReader
            .readInputAsStringList("2021/05_vent-lines.txt");

        List<VentRange> ventRanges = new ArrayList<>();
        for (String currentRawRange : rawVentRanges) {
            try {
                VentRange ventRange = VentRange.with(currentRawRange);
                ventRanges.add(ventRange);

            } catch (IllegalArgumentException ignored) {
                // continue
            }
        }
        VentDiagram ventDiagram = new VentDiagram(ventRanges);
        ventDiagram.update(ventRanges);
        int dangerousAreaCount = ventDiagram.countDangerousArea();
        System.out.println("Part I: " + dangerousAreaCount);
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawVentRanges = AdventFileReader
            .readInputAsStringList("2021/05_vent-lines.txt");

        List<VentRange> ventRanges = new ArrayList<>();
        for (String currentRawRange : rawVentRanges) {
            try {
                VentRange ventRange = VentRange.with(currentRawRange);
                ventRanges.add(ventRange);

            } catch (IllegalArgumentException ignored) {
                // continue
            }
        }
        VentDiagram ventDiagram = new VentDiagram(ventRanges);
        ventDiagram.updateDiagonal(ventRanges);
        int dangerousAreaCount = ventDiagram.countDangerousArea();
        System.out.println("Part II: " + dangerousAreaCount);
    }
}
