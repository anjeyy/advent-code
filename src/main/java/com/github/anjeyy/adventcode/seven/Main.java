package com.github.anjeyy.adventcode.seven;

import java.io.IOException;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
//        int uniqueAnswered = Arrays
//            .stream(
//                AdventFileReader
//                    .readInputAsString("seven_regulation-list.txt")
//                    .split(System.lineSeparator() + System.lineSeparator())
//            )
//            .map(String::trim)
//            .map(CustomsGroup::from)
//            .map(CustomsGroup::uniqueQuestionAnswered)
//            .reduce(0, Integer::sum);
//
//        System.out.println("Part I: " + uniqueAnswered);
    }

    private static void solvePartTwo() throws IOException {
//        int commonAnswered = Arrays
//            .stream(
//                AdventFileReader
//                    .readInputAsString("seven_regulation-list.txt")
//                    .split(System.lineSeparator() + System.lineSeparator())
//            )
//            .map(String::trim)
//            .map(CustomsGroup::from)
//            .map(CustomsGroup::commonQuestionAnswered)
//            .reduce(0, Integer::sum);
//
//        System.out.println("Part II: " + commonAnswered);
    }
}
