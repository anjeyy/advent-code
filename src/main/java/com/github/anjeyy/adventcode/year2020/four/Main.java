package com.github.anjeyy.adventcode.year2020.four;

import com.github.anjeyy.adventcode.AdventFileReader;
import com.github.anjeyy.adventcode.Constants;
import java.io.IOException;
import java.util.ArrayList;
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
        List<String> rawPassports = prepareInputData();

        long validPassports = rawPassports
            .stream()
            .map(String::trim)
            .map(Passport::from)
            .filter(Passport::isValidPartOne)
            .count();
        System.out.println("Part I: " + validPassports);
    }

    private static void solvePartTwo() throws IOException {
        List<String> rawPassports = prepareInputData();

        long validPassports = rawPassports
            .stream()
            .map(String::trim)
            .map(Passport::from)
            .filter(Passport::isValidPartTwo)
            .count();
        System.out.println("Part II: " + validPassports);
    }

    private static List<String> prepareInputData() throws IOException {
        List<String> passportLines = AdventFileReader.readInputAsStringList("four_passport-list.txt");

        StringBuilder tmp = new StringBuilder(Constants.EMPTY);
        List<String> rawPassports = new ArrayList<>();
        for (String line : passportLines) {
            tmp.append(line).append(Constants.WHITESPACE);
            boolean isBlank = line.equals(Constants.EMPTY) || line.equals(Constants.WHITESPACE);
            if (isBlank) {
                rawPassports.add(tmp.toString());
                tmp = new StringBuilder(Constants.EMPTY);
            }
        }
        rawPassports.add(tmp.toString()); // add the last concatenated line
        return rawPassports;
    }
}
