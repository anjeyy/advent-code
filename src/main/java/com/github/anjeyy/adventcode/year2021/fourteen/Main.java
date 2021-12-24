package com.github.anjeyy.adventcode.year2021.fourteen;

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
        List<String> rawInput = AdventFileReader.readInputAsStringList("2021/13_transparent-paper.txt");

        System.out.println("Part I: ");
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawInput = AdventFileReader.readInputAsStringList("2021/13_transparent-paper.txt");

        System.out.println("Part II: ");
    }
}
