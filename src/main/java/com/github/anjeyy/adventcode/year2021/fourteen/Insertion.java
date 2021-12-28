package com.github.anjeyy.adventcode.year2021.fourteen;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Insertion {

    private final Map<String, String> map;

    public Insertion(List<String> rawMap) {
        this.map = initialize(rawMap);
    }

    private static Map<String, String> initialize(List<String> rawMap) {
        return rawMap.stream()
                     .map(s -> s.split(" -> "))
                     .collect(Collectors.toMap(split -> split[0], split -> split[1]));
    }

    private void step(Polymer polymer) {
        for (int i = 0; i < 10; i++) {
            Deque<String> pairs = buildPairs(polymer);
            String currentPair = pairs.pop();   //todo -> List of pairs
            map.get(pairs.pop());
        }
    }

    private Deque<String> buildPairs(Polymer polymer) {
        Deque<String> deque = new ArrayDeque<>();
        String rawPolymer = polymer.getValue();
        char firstChar = rawPolymer.charAt(0);
        for (int i = 1; i < rawPolymer.length(); i++) {
            char secondChar = rawPolymer.charAt(i);
            String pair = "" + firstChar + secondChar;
            deque.push(pair);
        }
        return deque;
    }

    private String insertMiddle(){
        //todo - insert c in NN -> NCN
        return null;
    }
}
