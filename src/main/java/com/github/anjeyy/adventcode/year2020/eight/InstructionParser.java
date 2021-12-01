package com.github.anjeyy.adventcode.year2020.eight;

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

        private final int argument;
        private Operation operation;

        static Instruction with(Operation operation, int argument) {
            return new Instruction(operation, argument);
        }

        private Instruction(Operation operation, int argument) {
            if (operation == null) {
                throw new NullPointerException("Operation is not set - null.");
            }
            this.operation = operation;
            this.argument = argument;
        }

        Operation getOperation() {
            return operation;
        }

        int getArgument() {
            return argument;
        }

        void setOperation(Operation operation) {
            this.operation = operation;
        }

        @Override
        public String toString() {
            return "Instruction{" + "operation=" + operation + ", argument=" + argument + '}';
        }
    }

    static class InstructionModification {

        private final Instruction instruction;
        private boolean modified;

        static InstructionModification from(Instruction instruction) {
            return new InstructionModification(instruction);
        }

        private InstructionModification(Instruction instruction) {
            this.instruction = instruction;
            this.modified = (instruction.getOperation() == Operation.ACCUMULATOR); // exclude accumulator
        }

        Instruction getInstruction() {
            return instruction;
        }

        boolean isModified() {
            return modified;
        }

        boolean isNotModified() {
            return !modified;
        }

        void setModified(boolean modified) {
            this.modified = modified;
        }
    }
}
