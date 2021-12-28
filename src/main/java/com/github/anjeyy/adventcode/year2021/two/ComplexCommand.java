package com.github.anjeyy.adventcode.year2021.two;

import com.github.anjeyy.adventcode.Constants;
import java.util.Arrays;
import java.util.Optional;

class ComplexCommand {

    private static final long STARTING_HORIZONTAL = 0;
    private static final long STARTING_DEPTH = 0;
    private static final long STARTING_AIM = 0;

    private final Operation operation;
    private final long value;

    private ComplexCommand(String rawComplexCommand) {
        String[] splitCommand = rawComplexCommand.split(Constants.WHITESPACE);
        if (splitCommand.length != 2) {
            throw new IllegalArgumentException(String.format("Command arguments invalid: '%s'", rawComplexCommand));
        }
        this.operation = Operation.from(splitCommand[0]).orElseThrow();
        this.value = Long.parseLong(splitCommand[1]);
    }

    static ComplexCommand from(String rawComplexCommand) {
        return new ComplexCommand(rawComplexCommand);
    }

    long execute(long currentValue) {
        if (operation == Operation.DOWN) {
            return down(currentValue);
        } else if (operation == Operation.UP) {
            return up(currentValue);
        } else if (operation == Operation.FORWARD) {
            return forward(currentValue);
        }
        throw new IllegalStateException("Error: no operation could be identified: " + operation);
    }

    private long down(long currentValue) {
        return currentValue + value;
    }

    private long up(long currentValue) {
        return currentValue - currentValue;
    }

    private void forward(long currentHorizontal, long currentDepth, long currentValue) {
        currentHorizontal = currentHorizontal + value;
        currentDepth = currentValue * value;
    }

    enum Operation {
        DOWN,
        UP,
        FORWARD;

        static Optional<Operation> from(String rawCommand) {
            String upperCaseCommand = rawCommand.toUpperCase();
            return Arrays.stream(values()).filter(c -> c.name().equals(upperCaseCommand)).findAny();
        }
    }
}
