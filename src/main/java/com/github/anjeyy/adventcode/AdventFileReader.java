package com.github.anjeyy.adventcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class AdventFileReader {

    private static final String BASE_FILE_INPUT = "src/main/resources/input/";

    private AdventFileReader() {
        throw new UnsupportedOperationException("No instance allowed.");
    }

    public static String readInputAsString(String additionalPath) throws IOException {
        final String completePath = BASE_FILE_INPUT + additionalPath;
        Path path = Paths.get(completePath);
        return Files.readString(path);
    }

    public static List<String> readInputAsStringList(String additionalPath) throws IOException {
        final String completePath = BASE_FILE_INPUT + additionalPath;
        Path path = Paths.get(completePath);
        return Files.readAllLines(path);
    }

    public static Predicate<String> stringIsNotBlank() {
        return str -> !str.isBlank();
    }

}
