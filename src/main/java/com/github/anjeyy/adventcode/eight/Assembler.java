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

    static Assembler from(List<Instruction> instructions) {
        return new Assembler(instructions);
    }

    Assembler(List<Instruction> instructions) {
        this.instructions = initOrderedMap(instructions);
    }

    private static Map<Instruction, Boolean> initOrderedMap(List<Instruction> instructions) {
        Map<Instruction, Boolean> map = new LinkedHashMap<>(instructions.size());
        instructions.forEach(i -> map.put(i, false));
        return map;
    }

    int execute() {
        ListIterator<Entry<Instruction, Boolean>> entryIterator =
            new ArrayList<>(instructions.entrySet()).listIterator();
        boolean instructionIsUnique = true;
        int result = 0;
        while (instructionIsUnique) {
            if (!entryIterator.hasNext()) {
                break;
            }

            Map.Entry<Instruction, Boolean> currentEntry = entryIterator.next();
            if (currentEntry.getValue()) {
                instructionIsUnique = false;
            } else {
                currentEntry.setValue(true);
                Instruction currentInstruction = currentEntry.getKey();
                Operation currentOperation = currentInstruction.getOperation();
                int argument = currentInstruction.getArgument();
                if (currentOperation == Operation.NO_OPERATION) {
                    // do nothing
                } else if (currentOperation == Operation.ACCUMULATOR) {
                    result = result + argument;
                } else if (currentOperation == Operation.JUMPS) {
                    if (argument < 0) { // negative jumps
                        for (int i = 0; i >= argument; i--) {
                            if (entryIterator.hasPrevious()) {
                                entryIterator.previous();
                            } else {
                                throw new IllegalStateException("Jumped too high.");
                            }
                        }
                    } else if (argument > 0) {  // positive jumps
                        for (int i = 1; i < argument; i++) {
                            if (entryIterator.hasNext()) {
                                entryIterator.next();
                            } else {
                                throw new IllegalStateException("Jumped too high.");
                            }
                        }
                    } else {
                        throw new IllegalStateException("Jump does not support '0'.");
                    }
                }
            }
        }
        return result;
    }
}
