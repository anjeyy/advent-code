package com.github.anjeyy.adventcode.seven;

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
        Graph graph = new Graph();
        AdventFileReader
            .readInputAsStringList("seven_regulation-list.txt")
            .stream()
            .map(String::trim)
            .map(LineParser::from)
            .forEach(lp -> lp.parse(graph));

        Node shinyGold = Node.from("shiny gold");
        System.out.println("Part I: " + graph.countNodesFrom(shinyGold));
    }

    private static void solvePartTwo() throws IOException {
        Graph graph = new Graph();
        AdventFileReader
            .readInputAsStringList("seven_regulation-list.txt")
            .stream()
            .map(String::trim)
            .map(LineParser::from)
            .forEach(lp -> lp.parse(graph));

        Node shinyGold = Node.from("shiny gold");
        System.out.println("Part II: " + graph.countRecursivelyFrom(shinyGold));
    }
}
