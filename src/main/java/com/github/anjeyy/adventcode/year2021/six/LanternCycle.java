package com.github.anjeyy.adventcode.year2021.six;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LanternCycle {

    private final List<Lanternfish> lanternFishes;

    private LanternCycle(String rawInput) {
        this.lanternFishes = extractFishes(rawInput);
    }

    private static List<Lanternfish> extractFishes(String rawInput) {
        return Arrays.stream(rawInput.split(","))
                     .map(Integer::parseInt)
                     .map(Lanternfish::new)
                     .collect(Collectors.toCollection(LinkedList::new));
    }

    static LanternCycle from(String rawInput) {
        return new LanternCycle(rawInput);
    }

    int simulate(int days) {
        Supplier<Lanternfish> newLanternFish = () -> new Lanternfish(8);
        int newFishes = 0;
        for (int i = 0; i < days; i++) {
            System.out.printf("Day '%s' of '%s'.\n", i, days);
            for (Lanternfish currentFish : lanternFishes) {
                boolean fishSpawned = currentFish.simulateDay();
                if (fishSpawned) {
                    newFishes++;
                }
            }
            IntStream.range(0, newFishes).forEach(f -> lanternFishes.add(newLanternFish.get()));
            newFishes = 0;
        }
        return lanternFishes.size();
    }
}
