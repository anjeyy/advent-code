package com.github.anjeyy.adventcode.year2021.thirteen;

import com.github.anjeyy.adventcode.Constants;
import com.github.anjeyy.adventcode.year2021.thirteen.FoldInstruction.Kind;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

class TransparentPaper {

    private final List<FoldInstruction> foldInstructions;
    private String[][] paper;

    public TransparentPaper(List<Coordinate> coordinates, List<FoldInstruction> foldInstructions) {
        this.foldInstructions = foldInstructions;
        this.paper = initializePaper(coordinates);
    }

    private String[][] initializePaper(List<Coordinate> coordinates) {
        int rowSize = coordinates.stream().map(Coordinate::getY).mapToInt(v -> v).max().orElseThrow();
        int columnSize = coordinates.stream().map(Coordinate::getX).mapToInt(v -> v).max().orElseThrow();
        String[][] result = createInitialPaper(rowSize, columnSize);
        for (Coordinate currentCoordinate : coordinates) {
            int x = currentCoordinate.getX();
            int y = currentCoordinate.getY();
            result[y][x] = "#";
        }
        return result;
    }

    private static String[][] createInitialPaper(int rowSize, int columnSize) {
        String[][] result = new String[rowSize + 1][columnSize + 1];
        for (String[] strings : result) {
            Arrays.fill(strings, ".");
        }
        return result;
    }

    long foldFirst() {
        FoldInstruction firstFoldInstruction = firstFoldInstruction();
        if (firstFoldInstruction.getKind() == Kind.Y) {
            foldHorizontal(firstFoldInstruction);
        } else {
            foldVertical(firstFoldInstruction);
        }
        return countDots();
    }

    long foldAll() {
        for (FoldInstruction currentFold : foldInstructions) {
            if (currentFold.getKind() == Kind.Y) {
                foldHorizontal(currentFold);
            } else {
                foldVertical(currentFold);
            }
        }
        return countDots();
    }

    private FoldInstruction firstFoldInstruction() {
        return foldInstructions.stream().findFirst().orElseThrow();
    }

    private void foldHorizontal(FoldInstruction foldInstruction) {
        Entry<String[][], String[][]> horizontalSplit = splitHorizontalPaper(foldInstruction);
        String[][] upperPaper = horizontalSplit.getKey();
        String[][] lowerPaper = horizontalSplit.getValue();
        lowerPaper = reverseRows(lowerPaper);
        String[][] result;
        if (lowerPaper.length > upperPaper.length) {
            result = new String[lowerPaper.length][lowerPaper[0].length];
            int diff = lowerPaper.length - upperPaper.length;
            for (int i = 0; i < lowerPaper.length; i++) {
                for (int j = 0; j < lowerPaper[i].length; j++) {
                    String lowerHalfValue = lowerPaper[i][j];
                    String upperHalfValue;
                    if (i - diff >= 0) {
                        upperHalfValue = upperPaper[i][j];
                    } else {
                        upperHalfValue = Constants.EMPTY;
                    }
                    String resultValue =
                        (lowerHalfValue.equals("#") || upperHalfValue.equals("#")) ? "#" : Constants.EMPTY;
                    result[i][j] = resultValue;
                }
            }
        } else {
            result = new String[upperPaper.length][upperPaper[0].length];
            int diff = upperPaper.length - lowerPaper.length;
            for (int i = 0; i < upperPaper.length; i++) {
                for (int j = 0; j < upperPaper[i].length; j++) {
                    final String upperHalfValue = upperPaper[i][j];
                    String lowerHalfValue;
                    if (i - diff >= 0) {
                        lowerHalfValue = lowerPaper[i - diff][j];
                    } else {
                        lowerHalfValue = ".";
                    }
                    String resultValue =
                        (lowerHalfValue.equals("#") || upperHalfValue.equals("#")) ? "#" : ".";
                    result[i][j] = resultValue;
                }
            }
        }
        paper = result;
    }


    private Entry<String[][], String[][]> splitHorizontalPaper(FoldInstruction foldInstruction) {
        int yFold = foldInstruction.getValue();
        int rowSize = determineRowSize(foldInstruction);
        int columnSize = paper[0].length;
        String[][] upperPaper = new String[yFold][columnSize];
        String[][] lowerPaper = new String[rowSize][columnSize];

        for (int i = 0; i < upperPaper.length; i++) {
            System.arraycopy(paper[i], 0, upperPaper[i], 0, upperPaper[i].length);
        }
        for (int j = 0; j < lowerPaper.length; j++) {
            System.arraycopy(paper[j + (yFold + 1)], 0, lowerPaper[j], 0, lowerPaper[j].length);
        }
        return new SimpleEntry<>(upperPaper, lowerPaper);
    }

    private int determineRowSize(FoldInstruction foldInstruction) {
        if (foldInstruction.getKind() != FoldInstruction.Kind.Y) {
            throw new IllegalStateException(
                String.format("Cannot determine row for folding '%s'.", foldInstruction.getKind())
            );
        }
        int currentRowSize = paper.length;
        int yFold = foldInstruction.getValue();
        return (currentRowSize - 1) - yFold;
    }

    private String[][] reverseRows(String[][] lowerPaper) {
        String[][] reversedLowerPaper = new String[lowerPaper.length][lowerPaper[0].length];
        for (int i = 0; i < lowerPaper.length; i++) {
            reversedLowerPaper[(lowerPaper.length - 1) - i] = lowerPaper[i];
        }
        return reversedLowerPaper;
    }

    private void foldVertical(FoldInstruction foldInstruction) {
        Entry<String[][], String[][]> verticalSplit = splitVerticalPaper(foldInstruction);
        String[][] leftPaper = verticalSplit.getKey();
        String[][] rightPaper = verticalSplit.getValue();
        rightPaper = reverseColumns(rightPaper);
        String[][] result;
        if (rightPaper.length > leftPaper.length) {
            result = new String[rightPaper.length][rightPaper[0].length];
            int diff = rightPaper.length - leftPaper.length;
            for (int i = 0; i < rightPaper.length; i++) {
                for (int j = 0; j < rightPaper[i].length; j++) {
                    String rightHalfValue = rightPaper[i][j];
                    String leftHalfValue;
                    if (j - diff >= 0) {
                        leftHalfValue = leftPaper[i][j - diff];
                    } else {
                        leftHalfValue = ".";
                    }
                    String resultValue =
                        (rightHalfValue.equals("#") || leftHalfValue.equals("#")) ? "#" : ".";
                    result[i][j] = resultValue;
                }
            }
        } else {
            result = new String[leftPaper.length][leftPaper[0].length];
            int diff = leftPaper.length - rightPaper.length;
            for (int i = 0; i < leftPaper.length; i++) {
                for (int j = 0; j < leftPaper[i].length; j++) {
                    final String leftHalfValue = leftPaper[i][j];
                    String rightHalfValue;
                    if (i - diff >= 0) {
                        rightHalfValue = rightPaper[i][j];
                    } else {
                        rightHalfValue = ".";
                    }
                    String resultValue =
                        (rightHalfValue.equals("#") || leftHalfValue.equals("#")) ? "#" : ".";
                    result[i][j] = resultValue;
                }
            }
        }
        paper = result;
    }

    private Entry<String[][], String[][]> splitVerticalPaper(FoldInstruction foldInstruction) {
        int xFold = foldInstruction.getValue();
        int rowSize = paper.length;
        int columnSize = determineColumnSize(foldInstruction);
        String[][] leftPaper = new String[rowSize][xFold];
        String[][] rightPaper = new String[rowSize][columnSize];

        for (int i = 0; i < leftPaper.length; i++) {
            System.arraycopy(paper[i], 0, leftPaper[i], 0, leftPaper[i].length);
        }
        for (int j = 0; j < rightPaper.length; j++) {
            System.arraycopy(paper[j], (xFold + 1), rightPaper[j], 0, rightPaper[j].length);
//            System.arraycopy(paper[j + (xFold + 1)], 0, rightPaper[j], 0, rightPaper[j].length);
        }
        return new SimpleEntry<>(leftPaper, rightPaper);
    }

    private int determineColumnSize(FoldInstruction foldInstruction) {
        if (foldInstruction.getKind() != Kind.X) {
            throw new IllegalStateException(
                String.format("Cannot determine column for folding '%s'.", foldInstruction.getKind())
            );
        }
        int currentColumnSize = paper[0].length;
        int xFold = foldInstruction.getValue();
        int leftFoldSize = (currentColumnSize - 1) - xFold;
        return Math.max(leftFoldSize, xFold);
    }

    private String[][] reverseColumns(String[][] rightPaper) {
        String[][] reversedRightPaper = new String[rightPaper.length][rightPaper[0].length];
        for (int i = 0; i < rightPaper.length; i++) {
            for (int j = 0; j < rightPaper[i].length; j++) {
                reversedRightPaper[i][(rightPaper[i].length - 1) - j] = rightPaper[i][j];
            }
        }
        return reversedRightPaper;
    }

    private long countDots() {
        long result = 0L;
        for (String[] row : paper) {
            for (String value : row) {
                if (value.equals("#")) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String[] row : paper) {
            for (String value : row) {
                result.append(value);
            }
            result.append("\n");
        }
        return result.toString();
    }
}
