package com.github.anjeyy.adventcode.seven;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class LineParser {

    private static final String EMPTY = "";
    private static final String SOURCE_DESTINATION_SPLIT = "contain";
    private static final String DESTINATION_SPLIT = ",";


    private final String rawLine;

    static LineParser from(String rawLine) {
        return new LineParser(rawLine);
    }

    private LineParser(String rawLine) {
        this.rawLine = rawLine;
    }

    public static void main(String[] args) {
        //todo
        String input = "light red bags contain 1 bright white bag, 2 muted yellow bags.";
        LineParser.from(input).parse();
    }

    void parse() {
        String source = extractSourceVertex();
        List<String> destinations = extractDestinationVertices();

        //todo return Edge with Vertices

    }

    private String extractSourceVertex() {
        String source = sourceDestinationSplit()[0].trim();
        return replaceBags(source);
    }

    private List<String> extractDestinationVertices() {
        String rawDestination = sourceDestinationSplit()[1].trim()
                                                           .replace(".", EMPTY)
                                                           .replaceAll("\\d ", EMPTY);
        String polishedDestinations = replaceBags(rawDestination);
        String[] rawDestinations = polishedDestinations.split(DESTINATION_SPLIT);
        return Arrays.stream(rawDestinations).map(String::trim).collect(Collectors.toList());
    }

    private String[] sourceDestinationSplit() {
        String[] srcDestSplit = rawLine.split(SOURCE_DESTINATION_SPLIT);
        if (srcDestSplit.length < 2) {
            throw new IllegalStateException("Internal error - split not working correctly.");
        }
        return srcDestSplit;
    }

    private static String replaceBags(String value) {
        return value.replace("bags", EMPTY)
                    .replace("bag", EMPTY);
    }
}
