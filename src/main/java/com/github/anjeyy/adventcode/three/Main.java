package com.github.anjeyy.adventcode.three;

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
        TreeMapParser treeMapParser =
            AdventFileReader.readInputAsStringList("three_tree-map.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .collect(Collectors.collectingAndThen(
                                Collectors.toUnmodifiableList(),
                                TreeMapParser::from
                            ));

        System.out.println("Part I: " + treeMapParser.countTrees());
    }

    private static void solvePartTwo() throws IOException {
        //todo
    }
}
