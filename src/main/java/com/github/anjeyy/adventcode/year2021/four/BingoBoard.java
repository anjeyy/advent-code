package com.github.anjeyy.adventcode.year2021.four;

import com.github.anjeyy.adventcode.Constants;
import java.util.ArrayList;
import java.util.List;

class BingoBoard {

    private static final int BOARD_SIZE = 5;

    private final int[][] board;

    private int winningScore;

    private BingoBoard(List<String> rawBoard) {
        this.board = createBoard(rawBoard);
    }

    private static int[][] createBoard(List<String> rawBoard) {
        if (rawBoard.size() != BOARD_SIZE) {
            throw new IllegalArgumentException("Input board size is not five.");
        }
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < rawBoard.size(); i++) {
            String currentRawRow = rawBoard.get(i);
            int[] boardRow = createBoardRow(currentRawRow);
            board[i] = boardRow;
        }
        return board;
    }

    private static int[] createBoardRow(String rawBoardRow) {
        String[] splitRawBoardRow = splitAndCleanseEntries(rawBoardRow);
        if (splitRawBoardRow.length != BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("Input row of board size is not five: '%s'", rawBoardRow));
        }
        int[] row = new int[BOARD_SIZE];
        for (int i = 0; i < splitRawBoardRow.length; i++) {
            String rawValue = splitRawBoardRow[i];
            row[i] = Integer.parseInt(rawValue);
        }
        return row;
    }

    private static String[] splitAndCleanseEntries(String rawBoardRow) {
        String[] splitRawBoardRow = rawBoardRow.trim().split(Constants.WHITESPACE);
        List<String> cleansedRow = new ArrayList<>(BOARD_SIZE);
        for (String value : splitRawBoardRow) {
            if (!value.isBlank()) {
                cleansedRow.add(value);
            }
        }
        return cleansedRow.toArray(new String[0]);
    }

    static BingoBoard from(List<String> rawBoard) {
        return new BingoBoard(rawBoard);
    }

    boolean checkWith(int number) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == number) {
                    board[i][j] = number * (-1);
                    boolean gameWon = checkWinCondition(i, j);
                    if (gameWon) {
                        winningScore = number;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean checkWinCondition(int row, int column) {
        return checkFullRow(row) || checkFullColumn(column);
    }

    private boolean checkFullRow(int row) {
        for (int j = 0; j < board[row].length; j++) {
            if (board[row][j] >= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean checkFullColumn(int column) {
        for (int i = 0; i < board[column].length; i++) {
            if (board[i][column] >= 0) {
                return false;
            }
        }
        return true;
    }

    int calculateScore() {
        int result = 0;
        for (int[] row : board) {
            for (int value : row) {
                if (value > 0) {
                    result += value;
                }
            }
        }
        return result * winningScore;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int[] row : board) {
            for (int value : row) {
                result.append(value).append(Constants.WHITESPACE);
            }
            result.append(Constants.NEW_LINE);
        }
        return result.toString();
    }
}
