package com.github.anjeyy.adventcode.year2021.six;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.Map.Entry;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {

        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        LanternCycle lanternCycle = extractLanternCycle();
        int fishCount = lanternCycle.simulate(80);

        System.out.println("Part I: " + fishCount);
    }

    private static void solvePartTwo() throws IOException {
        String rawLanternfishCycle = AdventFileReader.readInputAsString("2021/six_lanternfish-cycle.txt");
        String[] rawFishy = rawLanternfishCycle.split(",");
        LanternFishMap fishMap = new LanternFishMap();
        for (String s : rawFishy) {
            Long value = Long.parseLong(s);
            fishMap.increment(value);
        }

        LanternFishMap newFishMap = new LanternFishMap();
        for (int i = 0; i < 256; i++) {
            for (Entry<Long, Long> currentFish : fishMap.entrySet()) {
                if (currentFish.getKey() == 0L) {
                    newFishMap.increment(8L, currentFish.getValue());
                    newFishMap.increment(6L, currentFish.getValue());
                } else {
                    newFishMap.increment(currentFish.getKey() - 1, currentFish.getValue());
                }
            }
            fishMap = newFishMap;
            newFishMap = new LanternFishMap();
        }

        System.out.println("Part II: " + fishMap.values().stream().mapToLong(e -> e).sum());
    }

    private static LanternCycle extractLanternCycle() throws IOException {
        String rawLanternfishCycle = AdventFileReader.readInputAsString("2021/six_lanternfish-cycle.txt");
        return LanternCycle.from(rawLanternfishCycle);
    }
}
