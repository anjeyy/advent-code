package com.github.anjeyy.adventcode.six;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CustomsGroup {

    private final Set<String> rawCustoms;

    static CustomsGroup from(String rawCustoms) {
        return new CustomsGroup(rawCustoms);
    }

    private CustomsGroup(String rawCustoms) {
        Objects.requireNonNull(rawCustoms, "Provide a list of customs form result.");
        List<String> customGroups = Arrays.asList(rawCustoms.split(System.lineSeparator()));
        this.rawCustoms = new HashSet<>(customGroups);
    }

    int uniqueQuestionAnswered() {
        Set<Character> uniqueAnsweredQuestions = new HashSet<>();
        for (String currentCustoms : rawCustoms) {
            char[] charArr = currentCustoms.toCharArray();
            for (Character curr : charArr) {
                uniqueAnsweredQuestions.add(curr);
            }
        }
        return uniqueAnsweredQuestions.size();
    }

    int commonQuestionAnswered() {
        if (rawCustoms.size() == 1) {
            return rawCustoms.stream().findFirst().orElseThrow().length();
        }
        char[] smallest = rawCustoms
            .stream()
            .min(Comparator.comparingInt(String::length))
            .map(String::toCharArray)
            .orElseThrow();
        Set<Character> commonAnsweredQuestions = IntStream
            .range(0, smallest.length)
            .mapToObj(i -> smallest[i])
            .collect(Collectors.toSet());
        Set<Character> result = new HashSet<>(commonAnsweredQuestions);
        
        for (String currentCustoms : rawCustoms) {
            char[] charArr = currentCustoms.toCharArray();
            Set<Character> currentCustom = IntStream
                .range(0, charArr.length)
                .mapToObj(i -> charArr[i])
                .collect(Collectors.toSet());
            for (Character currentChar : commonAnsweredQuestions) {
                if (!currentCustom.contains(currentChar)) {
                    result.remove(currentChar);
                }
            }
        }
        return result.size();
    }
}
