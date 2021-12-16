package com.github.anjeyy.adventcode.year2021.six;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.Arrays;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {

        System.out.println(15 / 7);
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
        long[] fishy = new long[rawFishy.length];
        for (int i = 0; i < rawFishy.length; i++) {
            fishy[i] = Long.parseLong(rawFishy[i]);
        }

        int newFish = 0;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < fishy.length; j++) {
                if (fishy[j] == 0) {
                    fishy[j] = 6;
                    newFish++;
                } else {
                    fishy[j]--;
                }
            }
            if (newFish > 0) {
                fishy = Arrays.copyOf(fishy, fishy.length + newFish);
                for (int j = fishy.length - 1; j > fishy.length - newFish - 1; j--) {
                    fishy[j] = 8;
                }
            }
        }
        int fishCount = fishy.length;

        System.out.println("Part II: " + fishCount);
    }

    private static LanternCycle extractLanternCycle() throws IOException {
        String rawLanternfishCycle = AdventFileReader.readInputAsString("2021/six_lanternfish-cycle.txt");
        return LanternCycle.from(rawLanternfishCycle);
    }
}
