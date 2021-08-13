package com.github.anjeyy.adventcode.nine;

import com.github.anjeyy.adventcode.AdventFileReader;
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
        List<Integer> instructions = AdventFileReader
            .readInputAsStringList("nine_encoding-list.txt")
            .stream()
            .map(String::trim)
            .map(Integer::valueOf)
            .collect(Collectors.toList());

        System.out.println(instructions);
//
//        Assembler assembler = Assembler.from(instructions);
//
//        System.out.println("Part I: " + assembler.execute());
    }

    private static void solvePartTwo() throws IOException {
//        List<Instruction> instructions = AdventFileReader
//            .readInputAsStringList("nine_encoding-list.txt")
//            .stream()
//            .map(String::trim)
//            .map(InstructionParser::from)
//            .collect(Collectors.toList());
//
//        Assembler assembler = Assembler.from(instructions);
//
//        System.out.println("Part II: " + assembler.fixLoophole());
    }
}
