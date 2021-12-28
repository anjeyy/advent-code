package com.github.anjeyy.adventcode.year2021.two;

import java.util.List;

class CommandProcessor {

    private static final int STARTING_HORIZONTAL = 0;
    private static final int STARTING_DEPTH = 0;

    private final List<CommandInstruction> commandInstructions;

    static CommandProcessor with(List<CommandInstruction> commandInstructions) {
        return new CommandProcessor(commandInstructions);
    }

    private CommandProcessor(List<CommandInstruction> commandInstructions) {
        this.commandInstructions = commandInstructions;
    }

    int navigate() {
        int resultHorizontal = STARTING_HORIZONTAL;
        int resultDepth = STARTING_DEPTH;
        for (CommandInstruction instruction : commandInstructions) {
            if (instruction.getCommandOperation() == CommandOperation.FORWARD) {
                resultHorizontal = instruction.execute(resultHorizontal);
            } else {
                resultDepth = instruction.execute(resultDepth);
            }
        }

        return resultHorizontal * resultDepth;
    }
}
