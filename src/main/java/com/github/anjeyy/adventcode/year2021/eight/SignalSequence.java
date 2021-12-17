package com.github.anjeyy.adventcode.year2021.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class SignalSequence {

    private final List<SignalEntry> signalEntries;

    SignalSequence(List<SignalEntry> signalEntries) {
        this.signalEntries = Objects.requireNonNullElse(signalEntries, new ArrayList<>());
    }

    int countUniqueSegments() {


        return -1;
    }
}
