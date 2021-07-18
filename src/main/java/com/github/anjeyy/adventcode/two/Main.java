package com.github.anjeyy.adventcode.two;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;

class Main {

    public static final String WHITESPACE = " ";

    public static void main(String[] args) throws IOException {

        long validPasswords =
            AdventFileReader.readInputAsStringList("two_password-list.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .map(PasswordParser::from)
                            .filter(PasswordParser::isValid)
                            .count();

        System.out.println(validPasswords);
    }
}
