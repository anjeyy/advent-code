package com.github.anjeyy.adventcode.eight;

import com.github.anjeyy.adventcode.eight.InstructionParser.Instruction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

class Assembler {

    private final Map<Instruction, Boolean> instructions;
    private final ListIterator<Entry<Instruction, Boolean>> instructionsIterator;

    static Assembler from(List<Instruction> instructions) {
        return new Assembler(instructions);
    }

    Assembler(List<Instruction> instructions) {
        this.instructions = initOrderedMap(instructions);
        this.instructionsIterator = new ArrayList<>(this.instructions.entrySet()).listIterator();
    }

    private static Map<Instruction, Boolean> initOrderedMap(List<Instruction> instructions) {
        Map<Instruction, Boolean> map = new LinkedHashMap<>(instructions.size());
        instructions.forEach(i -> map.put(i, false));
        return map;
    }

    int execute() {
        boolean instructionIsUnique = true;
        int result = 0;
        while (instructionIsUnique) {
            if (!instructionsIterator.hasNext()) {
                break;
            }

            Map.Entry<Instruction, Boolean> currentEntry = instructionsIterator.next();
            if (currentEntry.getValue()) {
                instructionIsUnique = false;
            } else {
                currentEntry.setValue(true);
                Instruction currentInstruction = currentEntry.getKey();
                Operation currentOperation = currentInstruction.getOperation();
                final int argument = currentInstruction.getArgument();
                result = selectOperation(currentOperation, result, argument);
            }
        }
        return result;
    }

    private int selectOperation(Operation operation, int result, int value) {
        switch (operation) {
            case NO_OPERATION:
                return result;
            case ACCUMULATOR:
                return result + value;
            case JUMPS:
                executeJump(value);
                return result;
            default:
                throw new IllegalStateException("Jump does not support '0'.");
        }
    }

    private void executeJump(int jumps) {
        if (jumps < 0) {
            jumpToPreviousPosition(jumps);
        } else if (jumps > 0) {
            jumpToNextPosition(jumps);
        } else {
            throw new IllegalStateException("Jump does not support '0'.");
        }
    }

    private void jumpToPreviousPosition(int jumps) {
        for (int i = 0; i >= jumps; i--) {
            if (instructionsIterator.hasPrevious()) {
                instructionsIterator.previous();
            } else {
                throw new IllegalStateException("Jumped too high.");
            }
        }
    }

    private void jumpToNextPosition(int jumps) {
        for (int i = 1; i < jumps; i++) {
            if (instructionsIterator.hasNext()) {
                instructionsIterator.next();
            } else {
                throw new IllegalStateException("Jumped too low.");
            }
        }
    }
}
