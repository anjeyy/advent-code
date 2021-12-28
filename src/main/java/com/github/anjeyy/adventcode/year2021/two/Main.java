package com.github.anjeyy.adventcode.year2021.two;

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
        List<CommandInstruction> commandInstructions = AdventFileReader
            .readInputAsStringList("2021/2_dive-commands.txt")
            .stream()
            .filter(AdventFileReader.stringIsNotBlank())
            .map(String::trim)
            .map(CommandInstruction::from)
            .collect(Collectors.toList());

        CommandProcessor commandProcessor = CommandProcessor.with(commandInstructions);

        System.out.println("Part I: " + commandProcessor.navigate());
    }

    private static void solvePartTwo() throws IOException {
        List<CommandInstruction> commandInstructions = AdventFileReader
            .readInputAsStringList("2021/2_dive-commands.txt")
            .stream()
            .filter(AdventFileReader.stringIsNotBlank())
            .map(String::trim)
            .map(CommandInstruction::from)
            .collect(Collectors.toList());

        CommandProcessor commandProcessor = CommandProcessor.with(commandInstructions);

        System.out.println("Part II: " + commandProcessor);
    }
}
