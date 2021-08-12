package com.github.anjeyy.adventcode.eight;

import com.github.anjeyy.adventcode.AdventFileReader;
import com.github.anjeyy.adventcode.eight.InstructionParser.Instruction;
import java.io.IOException;
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
        List<Instruction> instructions = AdventFileReader
            .readInputAsStringList("eight_instruction-list.txt")
            .stream()
            .map(String::trim)
            .map(InstructionParser::from)
            .collect(Collectors.toList());

        Assembler assembler = Assembler.from(instructions);

        System.out.println(assembler.execute());
//
//        System.out.println("Part I: " + graph.countNodesFrom(shinyGold));
    }

    private static void solvePartTwo() throws IOException {
//        Graph graph = new Graph();
//        AdventFileReader
//            .readInputAsStringList("seven_regulation-list.txt")
//            .stream()
//            .map(String::trim)
//            .map(LineParser::from)
//            .forEach(lp -> lp.parse(graph));
//
//        Node shinyGold = Node.from("shiny gold");
//        System.out.println("Part II: " + graph.countRecursivelyFrom(shinyGold));
    }
}
