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
        Assembler assembler = prepareData();
        System.out.println("Part I: " + assembler.execute());
    }

    private static void solvePartTwo() throws IOException {
        Assembler assembler = prepareData();
        System.out.println("Part II: " + assembler.fixLoophole());
    }

    private static Assembler prepareData() throws IOException {
        List<Instruction> instructions = AdventFileReader
            .readInputAsStringList("eight_instruction-list.txt")
            .stream()
            .map(String::trim)
            .map(InstructionParser::from)
            .collect(Collectors.toList());

        return Assembler.from(instructions);
    }
}
