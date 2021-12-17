package com.github.anjeyy.adventcode.year2021.nine;

import com.github.anjeyy.adventcode.AdventFileReader;
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
        AdventFileReader.readInputAsStringList("2021/09_smoke-heightmap.txt");

        System.out.println("Part I: ");
    }

    private static void solvePartTwo() throws IOException {
        AdventFileReader.readInputAsStringList("2021/09_smoke-heightmap.txt");

        System.out.println("Part II: ");
    }
}
