package com.github.anjeyy.adventcode.two;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        //todo main

        List<String> passwordList =
            AdventFileReader.readInputAsStringList("two_password-list.txt")
                            .stream()
                            .filter(AdventFileReader.stringIsNotBlank())
                            .map(String::trim)
                            .collect(Collectors.toList());

        //todo model domain for password and all the meanings
        System.out.println(passwordList);
    }
}
