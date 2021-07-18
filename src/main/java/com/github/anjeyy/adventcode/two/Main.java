package com.github.anjeyy.adventcode.two;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;

class Main {

    static final String WHITESPACE = " ";

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {

        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        long validPasswords =
            AdventFileReader.readInputAsStringList("two_password-list.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .map(PasswordParser::from)
                            .filter(PasswordParser::isValidPartOne)
                            .count();

        System.out.println("Part I: " + validPasswords);
    }

    private static void solvePartTwo() throws IOException {
        long validPasswords =
            AdventFileReader.readInputAsStringList("two_password-list.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .map(PasswordParser::from)
                            .filter(PasswordParser::isValidPartTwo)
                            .count();

        System.out.println("Part II: " + validPasswords);
    }
}
