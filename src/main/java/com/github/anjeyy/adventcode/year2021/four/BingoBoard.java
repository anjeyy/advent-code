package com.github.anjeyy.adventcode.year2021.four;

import com.github.anjeyy.adventcode.Constants;
import java.util.ArrayList;
import java.util.List;

class BingoBoard {

    private static final int BOARD_SIZE = 5;

    private final Double[][] board;

    private int winningScore;

    private BingoBoard(List<String> rawBoard) {
        this.board = createBoard(rawBoard);
    }

    private static Double[][] createBoard(List<String> rawBoard) {
        if (rawBoard.size() != BOARD_SIZE) {
            throw new IllegalArgumentException("Input board size is not five.");
        }
        Double[][] board = new Double[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < rawBoard.size(); i++) {
            String currentRawRow = rawBoard.get(i);
            Double[] boardRow = createBoardRow(currentRawRow);
            board[i] = boardRow;
        }
        return board;
    }

    private static Double[] createBoardRow(String rawBoardRow) {
        String[] splitRawBoardRow = splitAndCleanseEntries(rawBoardRow);
        if (splitRawBoardRow.length != BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("Input row of board size is not five: '%s'", rawBoardRow));
        }
        Double[] row = new Double[BOARD_SIZE];
        for (int i = 0; i < splitRawBoardRow.length; i++) {
            String rawValue = splitRawBoardRow[i];
            row[i] = Double.parseDouble(rawValue);
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
                    board[i][j] = board[i][j] * (-1);
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
            Double cellValue = board[row][j];
            if (isNonMarked(cellValue)){
                return false;
            }
        }
        return true;
    }

    private boolean checkFullColumn(int column) {
        for (int i = 0; i < board[column].length; i++) {
            Double cellValue = board[i][column];
            if (isNonMarked(cellValue)){
                return false;
            }
        }
        return true;
    }

    private static boolean isNonMarked(Double cellValue) {
        if (cellValue > 0) {
            return true;
        } else if (cellValue == 0 && !cellValue.equals(-0.0d)) {
            return true;
        }
        return false;
    }

    int calculateScore() {
        int result = 0;
        for (Double[] row : board) {
            for (Double value : row) {
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
        for (Double[] row : board) {
            for (Double value : row) {
                result.append(value).append(Constants.WHITESPACE);
            }
            result.append(Constants.NEW_LINE);
        }
        return result.toString();
    }
}
