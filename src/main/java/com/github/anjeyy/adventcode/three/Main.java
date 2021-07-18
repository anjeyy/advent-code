package com.github.anjeyy.adventcode.three;

import com.github.anjeyy.adventcode.AdventFileReader;
import com.github.anjeyy.adventcode.three.TreeMapParser.Slope;
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
        TreeMapParser treeMapParser =
            AdventFileReader.readInputAsStringList("three_tree-map.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .collect(Collectors.collectingAndThen(
                                Collectors.toUnmodifiableList(),
                                l -> TreeMapParser.fromAndSlop(l, Slope.fromRightAndDown(3, 1))
                            ));

        System.out.println("Part I: " + treeMapParser.countTrees());
    }

    private static void solvePartTwo() throws IOException {
        List<String> mapLines =
            AdventFileReader.readInputAsStringList("three_tree-map.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .collect(Collectors.toList());

        TreeMapParser treeMapParser1 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(1, 1));
        TreeMapParser treeMapParser2 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(3, 1));
        TreeMapParser treeMapParser3 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(5, 1));
        TreeMapParser treeMapParser4 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(7, 1));
        TreeMapParser treeMapParser5 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(1, 2));
        int result = treeMapParser1.countTrees() *
            treeMapParser2.countTrees() *
            treeMapParser3.countTrees() *
            treeMapParser4.countTrees() *
            treeMapParser5.countTrees();
        System.out.println("Part I: " + result);
    }
}
