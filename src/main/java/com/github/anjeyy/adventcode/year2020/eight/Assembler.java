package com.github.anjeyy.adventcode.year2020.eight;

import com.github.anjeyy.adventcode.year2020.eight.InstructionParser.Instruction;
import com.github.anjeyy.adventcode.year2020.eight.InstructionParser.InstructionModification;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

class Assembler {

    private final Map<Instruction, Boolean> instructions;
    private final ListIterator<Entry<InstructionModification, Boolean>> instructionsModifiedIterator;
    private final ListIterator<Entry<Instruction, Boolean>> instructionsIterator;

    private InstructionModification currentModification;

    static Assembler from(List<Instruction> instructions) {
        return new Assembler(instructions);
    }

    Assembler(List<Instruction> instructions) {
        this.instructions = initOrderedMap(instructions);
        this.instructionsIterator = new ArrayList<>(this.instructions.entrySet()).listIterator();
        this.instructionsModifiedIterator = initModifiedListIterator(this.instructions);
    }

    private static Map<Instruction, Boolean> initOrderedMap(List<Instruction> instructions) {
        Map<Instruction, Boolean> map = new LinkedHashMap<>(instructions.size());
        instructions.forEach(i -> map.put(i, false));
        return map;
    }

    private static ListIterator<Entry<InstructionModification, Boolean>> initModifiedListIterator(
        Map<Instruction, Boolean> instructions
    ) {
        Map<InstructionModification, Boolean> map = new LinkedHashMap<>(instructions.size());
        instructions.forEach((key, value) -> map.put(InstructionModification.from(key), false));
        List<Entry<InstructionModification, Boolean>> entryList = new ArrayList<>(map.entrySet());
        return entryList.listIterator();
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

    int fixLoophole() {
        currentModification = findFirstUnmodified();
        while (hasLoophole()) {
            resetModifiedInstruction();
            currentModification = findFirstUnmodified();
        }
        return execute();
    }

    private boolean hasLoophole() {
        execute();
        boolean hasLoophole = instructionsIterator.hasNext();
        resetIterator(instructionsIterator); // reset cursor
        instructions.entrySet().forEach(e -> e.setValue(false));
        return hasLoophole;
    }

    private void resetModifiedInstruction() {
        currentModification.setModified(true);
        alternateOperation(currentModification);
    }

    private InstructionModification findFirstUnmodified() {
        while (instructionsModifiedIterator.hasNext()) {
            Entry<InstructionModification, Boolean> currentEntry = instructionsModifiedIterator.next();
            InstructionModification instructionModification = currentEntry.getKey();
            if (instructionModification.isNotModified()) {
                alternateOperation(instructionModification);
                resetIterator(instructionsModifiedIterator);
                return instructionModification;
            }
        }
        throw new IllegalStateException("Should not happen.");
    }

    private static void alternateOperation(InstructionModification instructionModification) {
        Instruction instruction = instructionModification.getInstruction();
        if (instruction.getOperation() == Operation.NO_OPERATION) {
            if (instruction.getArgument() != 0) {
                instruction.setOperation(Operation.JUMPS);
            }
        } else if (instruction.getOperation() == Operation.JUMPS) {
            instruction.setOperation(Operation.NO_OPERATION);
        }
    }

    private static <E> void resetIterator(ListIterator<E> listIterator) {
        while (listIterator.hasPrevious()) {
            listIterator.previous();
        }
    }
}
