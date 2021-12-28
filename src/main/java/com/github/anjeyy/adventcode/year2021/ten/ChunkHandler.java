package com.github.anjeyy.adventcode.year2021.ten;

import com.github.anjeyy.adventcode.CollectionUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class ChunkHandler {

    private final Map<Bracket, Long> chunkMap = new EnumMap<>(Bracket.class);
    private final Deque<Bracket> openingBrackets = new ArrayDeque<>();
    private final List<Deque<Bracket>> incompleteOpeningBrackets = new ArrayList<>();

    private final List<String> chunks;

    private long corruptedScore = 0L;


    private ChunkHandler(List<String> chunks) {
        this.chunks = chunks;
    }

    static ChunkHandler from(List<String> chunks) {
        return new ChunkHandler(chunks);
    }

    long calculateIncompleteScore() {
        calculateCorruptedScore();
        List<Long> result = new ArrayList<>();
        for (Deque<Bracket> currentOpeningBrackets : incompleteOpeningBrackets) {
            long lineResult = 0L;
            while (CollectionUtils.isNotEmpty(currentOpeningBrackets)) {
                Bracket bracket = currentOpeningBrackets.pop();
                lineResult = (lineResult * 5L) + bracket.closingScore();
            }
            result.add(lineResult);
        }
        Collections.sort(result);
        int index = result.size() / 2;
        return result.get(index);
    }

    long calculateCorruptedScore() {
        corruptedScore = 0L;
        for (String currentChunkLine : chunks) {
            boolean skip = false;
            final char[] chunkChars = currentChunkLine.toCharArray();
            for (char currentChar : chunkChars) {
                appendOpeningBracket(currentChar);
                skip = decreaseClosingBracket(currentChar);
                if (skip) {
                    break;
                }
            }
            if (!skip) {
                Deque<Bracket> deepCopy = CollectionUtils.createDequeDeepCopy(openingBrackets);
                incompleteOpeningBrackets.add(deepCopy);
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
