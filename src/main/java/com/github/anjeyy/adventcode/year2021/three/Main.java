package com.github.anjeyy.adventcode.year2021.three;

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
        List<String> rawDiagnosticReport = extractRawDiagnosticReport();

        BitSequenceHandler bitSequenceHandler = BitSequenceHandler.from(rawDiagnosticReport);
        long gammaBitAsDecimal = bitSequenceHandler.calculateGammaRate();
        long epsilonBitAsDecimal = bitSequenceHandler.calculateEpsilonRate();

        System.out.println("Part I: " + gammaBitAsDecimal * epsilonBitAsDecimal);
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawDiagnosticReport = extractRawDiagnosticReport();

        BitSequenceHandler bitSequenceHandler = BitSequenceHandler.from(rawDiagnosticReport);
        long oxygenBitAsDecimal = bitSequenceHandler.calculateOxygenRate();
        long co2ScrubberAsDecimal = bitSequenceHandler.calculateCo2ScrubberRate();

        System.out.println("Part II: " + oxygenBitAsDecimal * co2ScrubberAsDecimal);
    }

    private static List<String> extractRawDiagnosticReport() throws IOException {
        return AdventFileReader
                .readInputAsStringList("2021/03_diagnostic-report.txt")
                .stream()
                .filter(AdventFileReader.stringIsNotBlank())
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
