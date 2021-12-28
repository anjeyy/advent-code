package com.github.anjeyy.adventcode.year2020.three;

import com.github.anjeyy.adventcode.AdventFileReader;
import com.github.anjeyy.adventcode.year2020.three.TreeMapParser.Slope;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        TreeMapParser treeMapParser = readInput()
            .collect(
                Collectors.collectingAndThen(
                    Collectors.toUnmodifiableList(),
                    l -> TreeMapParser.fromAndSlop(l, Slope.fromRightAndDown(3, 1))
                )
            );

        System.out.println("Part I: " + treeMapParser.countTrees());
    }

    private static void solvePartTwo() throws IOException {
        List<String> mapLines = readInput().collect(Collectors.toList());

        TreeMapParser treeMapParser1 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(1, 1));
        TreeMapParser treeMapParser2 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(3, 1));
        TreeMapParser treeMapParser3 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(5, 1));
        TreeMapParser treeMapParser4 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(7, 1));
        TreeMapParser treeMapParser5 = TreeMapParser.fromAndSlop(mapLines, Slope.fromRightAndDown(1, 2));
        int result =
            treeMapParser1.countTrees() *
            treeMapParser2.countTrees() *
            treeMapParser3.countTrees() *
            treeMapParser4.countTrees() *
            treeMapParser5.countTrees();

        System.out.println("Part II: " + result);
    }

    private static Stream<String> readInput() throws IOException {
        return AdventFileReader
            .readInputAsStringList("2020/03_tree-map.txt")
            .stream()
            .filter(AdventFileReader.stringIsNotBlank())
            .map(String::trim);
    }
}
