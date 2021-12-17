package com.github.anjeyy.adventcode.year2021.eight;

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
        SignalSequence signalSequence =
            AdventFileReader.readInputAsStringList("2021/eight_segment-wire.txt")
                            .stream()
                            .map(SignalEntry::new)
                            .collect(Collectors.collectingAndThen(Collectors.toList(), SignalSequence::new));

        System.out.println("Part I: " + signalSequence.countUniqueSegments());
    }

    private static void solvePartTwo() throws IOException {
        SignalSequence signalSequence =
            AdventFileReader.readInputAsStringList("2021/eight_segment-wire.txt")
                            .stream()
                            .map(SignalEntry::new)
                            .collect(Collectors.collectingAndThen(Collectors.toList(), SignalSequence::new));

        System.out.println("Part II: " + signalSequence.sumAllOutputs());
    }
}
