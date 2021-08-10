package com.github.anjeyy.adventcode.eight;

class InstructionParser {

    private InstructionParser() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    static Instruction from(String instructionLine) {
        String[] instructions = instructionLine.trim().split(" ");
        if (instructions.length != 2) {
            throw new IllegalArgumentException("");
        }
        Operation operation = Operation.from(instructions[0]);
        int argument = Integer.parseInt(instructions[1]);
        return Instruction.with(operation, argument);
    }

    static class Instruction {

        private final Operation operation;
        private final int argument;

        static Instruction with(Operation operation, int argument) {
            return new Instruction(operation, argument);
        }

        private Instruction(Operation operation, int argument) {
            this.operation = operation;
            this.argument = argument;
        }

        Operation getOperation() {
            return operation;
        }

        int getArgument() {
            return argument;
        }

        @Override
        public String toString() {
            return "Instruction{" +
                "operation=" + operation +
                ", argument=" + argument +
                '}';
        }
    }
}
