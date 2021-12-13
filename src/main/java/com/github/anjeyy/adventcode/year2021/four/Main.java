package com.github.anjeyy.adventcode.year2021.four;

import com.github.anjeyy.adventcode.AdventFileReader;
import com.github.anjeyy.adventcode.CollectionUtils;
import com.github.anjeyy.adventcode.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        String bingoDraws = extractRawBingoDraws();
        BingoDrawer bingoDrawer = BingoDrawer.from(bingoDraws);
        List<List<String>> rawStructuredBingoBoards = extractRawBingoBoards();
        List<BingoBoard> bingoBoards = rawStructuredBingoBoards.stream()
                                                               .map(BingoBoard::from)
                                                               .collect(Collectors.toList());
        BingoGame bingoGame = BingoGame.with(bingoBoards, bingoDrawer);
        int result = bingoGame.start();
        System.out.println("Part I: " + result);
    }

    private static void solvePartTwo() throws IOException {
        String bingoDraws = extractRawBingoDraws();
        BingoDrawer bingoDrawer = BingoDrawer.from(bingoDraws);
        List<List<String>> rawStructuredBingoBoards = extractRawBingoBoards();
        List<BingoBoard> bingoBoards = rawStructuredBingoBoards.stream()
                                                               .map(BingoBoard::from)
                                                               .collect(Collectors.toList());
        BingoGame bingoGame = BingoGame.with(bingoBoards, bingoDrawer);
        int result = bingoGame.winLast();
        System.out.println("Part I: " + result);
    }

    private static String extractRawBingoDraws() throws IOException {
        return AdventFileReader
            .readInputAsStringList("2021/four_bingo-board.txt")
            .stream()
            .findFirst()
            .orElseThrow();
    }

    private static List<List<String>> extractRawBingoBoards() throws IOException {
        List<String> rawInput = AdventFileReader
            .readInputAsStringList("2021/four_bingo-board.txt");
        List<String> rawBingoBoards = rawInput.subList(2, rawInput.size())
                                              .stream()
                                              .map(String::trim)
                                              .collect(Collectors.toList());
        rawBingoBoards.add(Constants.EMPTY);    // ending condition
        List<List<String>> rawStructuredBingoBoards = new ArrayList<>();
        List<String> rawBoard = new ArrayList<>();
        for (String currentRawRow : rawBingoBoards) {
            if (currentRawRow.isBlank() && CollectionUtils.isNotEmpty(rawBoard)) {
                rawStructuredBingoBoards.add(rawBoard);
                rawBoard = new ArrayList<>();
                continue;
            }
            rawBoard.add(currentRawRow);
        }
        return rawStructuredBingoBoards;
    }
}
