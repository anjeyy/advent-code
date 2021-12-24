package com.github.anjeyy.adventcode.year2021.fourteen;

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
        List<String> rawInput = AdventFileReader.readInputAsStringList("2021/14_polymer-template.txt");
        Polymer polymer = rawInput.stream()
                                  .takeWhile(StringUtils::isNotBlank)
                                  .findFirst()
                                  .map(Polymer::of)
                                  .orElseThrow();

        Collections.reverse(rawInput);
        Insertion insertion = rawInput.stream()
                                      .takeWhile(StringUtils::isNotBlank)
                                      .collect(Collectors.collectingAndThen(Collectors.toList(), Insertion::new));

        System.out.println("Part I: ");
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawInput = AdventFileReader.readInputAsStringList("2021/14_polymer-template.txt");

        System.out.println("Part II: ");
    }
}
