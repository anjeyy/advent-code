package com.github.anjeyy.adventcode.year2021.two;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.IntBinaryOperator;

enum CommandOperation {
    FORWARD(Integer::sum),
    DOWN(Integer::sum),
    UP((a, b) -> a - b);

    private final IntBinaryOperator intBinaryOperator;

    CommandOperation(IntBinaryOperator intBinaryOperator) {
        this.intBinaryOperator = intBinaryOperator;
    }

    int execute(int currentValue, int newValue) {
        return intBinaryOperator.applyAsInt(currentValue, newValue);
    }

    static Optional<CommandOperation> from(String rawCommand) {
        String upperCaseCommand = rawCommand.toUpperCase();
        return Arrays.stream(values()).filter(c -> c.name().equals(upperCaseCommand)).findAny();
    }
}
