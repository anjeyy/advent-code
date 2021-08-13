package com.github.anjeyy.adventcode.nine;

import java.util.Arrays;
import java.util.List;

class Preamble {

    private final int[] numbers;

    static Preamble with(List<Integer> numbers) {
        return new Preamble(numbers);
    }

    public Preamble(List<Integer> numbers) {
        this.numbers = initArray(numbers);
    }

    private static int[] initArray(List<Integer> numbers) {
        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }
        return result;
    }

    void add(int newNumber) {
        for (int i = 0; i < numbers.length - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        numbers[numbers.length - 1] = newNumber;
    }

    boolean contains(int number) {
        return Arrays.stream(numbers).anyMatch(i -> i == number);
    }

    boolean isSumOfTwoNumbers(int number) {
        //todo

        return false;
    }
}
