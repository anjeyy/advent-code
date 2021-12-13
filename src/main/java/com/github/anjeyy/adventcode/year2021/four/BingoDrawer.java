package com.github.anjeyy.adventcode.year2021.four;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

class BingoDrawer {

    private final Queue<Integer> draws;

    private BingoDrawer(String rawDraws) {
        this.draws = createDraws(rawDraws);
    }

    private static Queue<Integer> createDraws(String rawDraws) {
        String[] splitRawDraws = rawDraws.split(",");
        Queue<Integer> result = new ArrayDeque<>(splitRawDraws.length);
        for (String splitRawDraw : splitRawDraws) {
            Integer parseInt = Integer.parseInt(splitRawDraw);
            result.offer(parseInt);
        }
        return result;
    }

    static BingoDrawer from(String rawDraws) {
        return new BingoDrawer(rawDraws);
    }

    Optional<Integer> draw() {
        return Optional.of(draws).map(Queue::poll);
    }
}
