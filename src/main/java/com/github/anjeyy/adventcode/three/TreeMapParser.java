package com.github.anjeyy.adventcode.three;

import java.util.List;

class TreeMapParser {

    private static final char TREE = '#';

    private final List<String> mapLines;

    private TreeMapParser(List<String> mapLines) {
        this.mapLines = mapLines;
    }

    static TreeMapParser from(List<String> mapLines) {
        return new TreeMapParser(mapLines);
    }

    int countTrees() {
        int counter = 0;
        int position = 0;
        for (String line : mapLines) {
            if (isTree(position, line)) {
                counter++;
            }
            position = stepThrough(position, line);
        }
        return counter;
    }

    private boolean isTree(int position, String line) {
        return line.charAt(position) == TREE;
    }

    private int stepThrough(int position, String line) {
        position = position + 3;
        if (position >= line.length()) {
            position = position - line.length(); // reset to the left
        }
        return position;
    }

}
