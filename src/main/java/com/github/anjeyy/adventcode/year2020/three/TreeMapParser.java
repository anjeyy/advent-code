package com.github.anjeyy.adventcode.year2020.three;

import java.util.List;

class TreeMapParser {

    private static final char TREE = '#';

    private final List<String> mapLines;
    private final Slope slope;

    private TreeMapParser(List<String> mapLines, Slope slope) {
        this.mapLines = mapLines;
        this.slope = slope;
    }

    static TreeMapParser fromAndSlop(List<String> mapLines, Slope slope) {
        return new TreeMapParser(mapLines, slope);
    }

    int countTrees() {
        int counter = 0;
        int position = 0;

        for (int i = 0; i < mapLines.size(); i = i + slope.down) {
            final String line = mapLines.get(i);
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
        position = position + slope.right;
        if (position >= line.length()) {
            position = position - line.length(); // reset to the left
        }
        return position;
    }

    static class Slope {

        private final int right;
        private final int down;

        private Slope(int right, int down) {
            this.right = right;
            this.down = down;
        }

        static Slope fromRightAndDown(int right, int down) {
            return new Slope(right, down);
        }
    }
}
