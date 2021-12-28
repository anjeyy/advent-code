package com.github.anjeyy.adventcode.year2021.two;

import com.github.anjeyy.adventcode.Constants;

class CommandInstruction {

    private final CommandOperation commandOperation;
    private final int value;

    static CommandInstruction from(String rawCommand) {
        return new CommandInstruction(rawCommand);
    }

    private CommandInstruction(String rawCommand) {
        String[] splitCommand = rawCommand.split(Constants.WHITESPACE);
        if (splitCommand.length != 2) {
            throw new IllegalArgumentException(String.format("Command arguments invalid: '%s'", rawCommand));
        }
        this.commandOperation = CommandOperation.from(splitCommand[0]).orElseThrow();
        this.value = Integer.parseInt(splitCommand[1]);
    }

    public CommandOperation getCommandOperation() {
        return commandOperation;
    }

    int execute(int currentValue) {
        return commandOperation.execute(currentValue, value);
    }
}
