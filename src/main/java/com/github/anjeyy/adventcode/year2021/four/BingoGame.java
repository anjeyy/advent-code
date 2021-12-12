package com.github.anjeyy.adventcode.year2021.four;

import java.util.List;
import java.util.Optional;

class BingoGame {

    private final List<BingoBoard> bingoBoards;
    private final BingoDrawer bingoDrawer;

    private BingoGame(List<BingoBoard> bingoBoards, BingoDrawer bingoDrawer) {
        this.bingoBoards = bingoBoards;
        this.bingoDrawer = bingoDrawer;
    }

    static BingoGame with(List<BingoBoard> bingoBoards, BingoDrawer bingoDrawer) {
        return new BingoGame(bingoBoards, bingoDrawer);
    }

    int start() {
        Optional<Integer> drawnNumber;
        boolean gameIsActive = true;
        while (gameIsActive) {
            drawnNumber = bingoDrawer.draw();
            if (drawnNumber.isPresent()) {
                for (BingoBoard currentBoard : bingoBoards) {
                    boolean gameWon = currentBoard.checkWith(drawnNumber.get());
                    if (gameWon) {
                        return currentBoard.calculateScore();
                    }
                }
            } else {
                gameIsActive = false;
            }
        }
        return -1;
    }
}
