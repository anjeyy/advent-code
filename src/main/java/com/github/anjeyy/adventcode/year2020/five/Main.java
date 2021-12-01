package com.github.anjeyy.adventcode.year2020.five;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
        int maxSeatId = AdventFileReader
            .readInputAsStringList("five_boarding-list.txt")
            .stream()
            .map(String::trim)
            .map(SeatParser::from)
            .map(s -> Seat.of(s.determineRow(), s.determineColumn()))
            .map(Seat::getSeatId)
            .max(Comparator.naturalOrder())
            .orElseThrow();

        System.out.println("Part I: " + maxSeatId);
    }

    private static void solvePartTwo() throws IOException {
        Map<Integer, Seat> seatList = AdventFileReader
            .readInputAsStringList("five_boarding-list.txt")
            .stream()
            .map(String::trim)
            .map(SeatParser::from)
            .map(s -> Seat.of(s.determineRow(), s.determineColumn()))
            .collect(Collectors.toMap(Seat::getSeatId, Function.identity()));

        int maxSeatId = 127 * 8 + 7; // 1023
        List<Integer> potentialSeats = new ArrayList<>();
        for (int i = 1; i < maxSeatId - 1; i++) {
            Seat currentSeat = seatList.get(i);
            if (currentSeat == null) {
                Seat previousSeat = seatList.get(i - 1);
                if (previousSeat != null) {
                    Seat followingSeat = seatList.get(i + 1);
                    {
                        if (followingSeat != null) {
                            potentialSeats.add(i);
                        }
                    }
                }
            }
        }

        System.out.println("Part II: ");
        potentialSeats.forEach(System.out::println);
    }
}
