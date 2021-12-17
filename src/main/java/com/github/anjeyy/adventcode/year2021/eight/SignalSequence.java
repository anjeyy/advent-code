package com.github.anjeyy.adventcode.year2021.eight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

class SignalSequence {

    private final List<SignalEntry> signalEntries;

    SignalSequence(List<SignalEntry> signalEntries) {
        this.signalEntries = Objects.requireNonNullElse(signalEntries, new ArrayList<>());
    }

    long countUniqueSegments() {
        return signalEntries.stream()
                            .map(SignalEntry::getOutput)
                            .flatMap(Collection::stream)
                            .filter(Output::isUniqueDigit)
                            .count();
    }
}
