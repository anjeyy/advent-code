package com.github.anjeyy.adventcode.eight;

import com.github.anjeyy.adventcode.eight.InstructionParser.Instruction;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    //todo prettier
    int execute() {
        Iterator<Map.Entry<Instruction, Boolean>> entryIterator = instructions.entrySet().iterator();
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
                //todo execute commands
                Operation currentOperation = currentEntry.getKey().getOperation();
                if (currentOperation == Operation.NO_OPERATION) {
                    // do nothing
                } else if (currentOperation == Operation.ACCUMULATOR) {
                    result = result + currentEntry.getKey().getArgument();
                } else if (currentOperation == Operation.JUMPS) {
                    //todo positive jumps

                    //todo negative jumps

                }
            }
        }

        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
