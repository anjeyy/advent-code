package com.github.anjeyy.adventcode.six;

import com.github.anjeyy.adventcode.AdventFileReader;
import java.io.IOException;
import java.util.Arrays;

class Main {

    private Main() {
        throw new UnsupportedOperationException("No instance allowed");
    }

    public static void main(String[] args) throws IOException {
        solvePartOne();

        solvePartTwo();
    }

    private static void solvePartOne() throws IOException {
        int uniqueAnswered = Arrays
            .stream(AdventFileReader.readInputAsString("six_customs-list.txt").split("\n\n"))
            .map(String::trim)
            .map(CustomsGroup::from)
            .map(CustomsGroup::uniqueQuestionAnswered)
            .reduce(0, Integer::sum);

        System.out.println("Part I: " + uniqueAnswered);
    }

    private static void solvePartTwo() throws IOException {
        //        Map<Integer, Seat> seatList = AdventFileReader
        //            .readInputAsStringList("six_customs-list.txt")
        //            .stream()
        //            .map(String::trim)
        //            .map(SeatParser::from)
        //            .map(s -> Seat.of(s.determineRow(), s.determineColumn()))
        //            .collect(Collectors.toMap(Seat::getSeatId, Function.identity()));
        //
        //        int maxSeatId = 127 * 8 + 7; // 1023
        //        List<Integer> potentialSeats = new ArrayList<>();
        //        for (int i = 1; i < maxSeatId - 1; i++) {
        //            Seat currentSeat = seatList.get(i);
        //            if (currentSeat == null) {
        //                Seat previousSeat = seatList.get(i - 1);
        //                if (previousSeat != null) {
        //                    Seat followingSeat = seatList.get(i + 1);
        //                    {
        //                        if (followingSeat != null) {
        //                            potentialSeats.add(i);
        //                        }
        //                    }
        //                }
        //            }
        //        }
        //
        //        System.out.println("Part II: " + maxSeatId);

    }
}
