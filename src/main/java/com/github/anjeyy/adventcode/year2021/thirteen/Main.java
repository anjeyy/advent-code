package com.github.anjeyy.adventcode.year2021.thirteen;

import com.github.anjeyy.adventcode.AdventFileReader;
import com.github.anjeyy.adventcode.StringUtils;
import java.io.IOException;
import java.util.Collections;
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
        List<String> rawInput = AdventFileReader.readInputAsStringList("2021/13_transparent-paper.txt");
        List<Coordinate> dots = prepareCoordinates(rawInput);
        List<FoldInstruction> foldInstruction = prepareFoldInstruction(rawInput);
        TransparentPaper transparentPaper = new TransparentPaper(dots, foldInstruction);

        System.out.println("Part I: " + transparentPaper.foldFirst());
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawInput = AdventFileReader.readInputAsStringList("2021/13_transparent-paper.txt");
        List<Coordinate> dots = prepareCoordinates(rawInput);
        List<FoldInstruction> foldInstruction = prepareFoldInstruction(rawInput);
        TransparentPaper transparentPaper = new TransparentPaper(dots, foldInstruction);

        System.out.println("Part II: " + transparentPaper.foldAll());
    }

    private static List<Coordinate> prepareCoordinates(List<String> rawInput) {
        List<Coordinate> result = rawInput.stream()
                                          .takeWhile(StringUtils::isNotBlank)
                                          .map(Coordinate::from)
                                          .collect(Collectors.toList());
        Collections.reverse(rawInput);
        return result;
    }

    private static List<FoldInstruction> prepareFoldInstruction(List<String> rawInput){
        List<FoldInstruction> result = rawInput.stream()
                                                        .takeWhile(StringUtils::isNotBlank)
                                                        .map(FoldInstruction::of)
                                                        .collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }
}
