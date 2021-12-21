package com.github.anjeyy.adventcode.year2021.ten;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class ChunkHandler {

    private final Map<Bracket, Long> chunkMap = new EnumMap<>(Bracket.class);
    private final Deque<Bracket> openingBrackets = new ArrayDeque<>();

    private final List<String> chunks;

    private long corruptedScore = 0L;


    private ChunkHandler(List<String> chunks) {
        this.chunks = chunks;
    }

    static ChunkHandler from(List<String> chunks) {
        return new ChunkHandler(chunks);
    }

    long calculateCorruptedScore() {
        corruptedScore = 0L;
        for (String currentChunkLine : chunks) {
            final char[] chunkChars = currentChunkLine.toCharArray();
            for (char currentChar : chunkChars) {
                appendOpeningBracket(currentChar);
                boolean skip = decreaseClosingBracket(currentChar);
                if (skip) {
                    break;
                }
            }
            reset();
        }
        return corruptedScore;
    }

    private void appendOpeningBracket(char currentChar) {
        if (Bracket.isNotOpening(currentChar)) {
            return;
        }
        Bracket bracket = Bracket.from(currentChar);
        openingBrackets.push(bracket);
        Long bracketCount = chunkMap.get(bracket);
        if (bracketCount == null) {
            chunkMap.put(bracket, 1L);
        } else {
            bracketCount++;
            chunkMap.put(bracket, bracketCount);
        }
    }

    private boolean decreaseClosingBracket(char currentChar) {
        if (Bracket.isNotClosing(currentChar)) {
            return false;
        }
        Bracket bracket = Bracket.from(currentChar);
        Bracket toBeClosed = openingBrackets.peek();
        Long bracketCount = chunkMap.get(bracket);
        if (bracketCount == null || bracketCount == 0L || bracket != toBeClosed) {
            corruptedScore += Bracket.score(currentChar);
            return true;
        } else {
            openingBrackets.pop();
            bracketCount--;
            chunkMap.put(bracket, bracketCount);
            return false;
        }
    }

    private void reset() {
        chunkMap.clear();
        openingBrackets.clear();
    }
}
