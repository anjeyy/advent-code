package com.github.anjeyy.adventcode.six;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class CustomsGroup {

    private final Set<String> rawCustoms;

    static CustomsGroup from(String rawCustoms) {
        return new CustomsGroup(rawCustoms);
    }

    private CustomsGroup(String rawCustoms) {
        Objects.requireNonNull(rawCustoms, "Provide a list of customs form result.");
        List<String> customGroups = Arrays.asList(rawCustoms.split("\n"));
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
}
